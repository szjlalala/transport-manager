package com.tms.service.impl;


import com.tms.common.BizException;
import com.tms.common.Results;
import com.tms.controller.vo.request.CreateOrderRequestVo;
import com.tms.controller.vo.request.QueryOrderRequestVo;
import com.tms.controller.vo.response.OrderDetailResponseVo;
import com.tms.controller.vo.response.OrderResponseVo;
import com.tms.model.CustomerOrder;
import com.tms.model.CustomerOrderDetail;
import com.tms.model.DeliverOrder;
import com.tms.model.Payment;
import com.tms.repository.CustomerOrderDetailRepository;
import com.tms.repository.CustomerOrderRepository;
import com.tms.repository.DeliverOrderRepository;
import com.tms.repository.SysCodeRepository;
import com.tms.service.CustomerOrderService;
import com.tms.service.DeliverOrderService;
import com.tms.service.MQProducer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private DeliverOrderService deliverOrderService;
    @Autowired
    private SysCodeRepository sysCodeRepository;
    @Autowired
    private DeliverOrderRepository deliverOrderRepository;
    @Autowired
    private MQProducer mqProducer;
    @Autowired
    private CustomerOrderDetailRepository customerOrderDetailRepository;

    @Override
    public String createCustomerOrder(CreateOrderRequestVo createOrderRequestVo) {
        //验证
        validate(createOrderRequestVo);
        //生成用户订单
        CustomerOrder customerOrder = new CustomerOrder(createOrderRequestVo);
        customerOrder = customerOrderRepository.save(customerOrder);
        //生成运单
        for (CustomerOrderDetail customerOrderDetail : customerOrder.getOrderDetails()) {
            deliverOrderService.createDeliverOrder(customerOrderDetail);
        }
        //将运单广播
        for (CustomerOrderDetail customerOrderDetail : customerOrder.getOrderDetails()) {
            List<DeliverOrder> deliverOrders = deliverOrderRepository.findByCustomerOrderDetailAndDeliverOrderStateOrderBySequenceAsc(customerOrderDetail, DeliverOrder.DeliverOrderState.UNALLOCATED);
            deliverOrderService.spreadDeliverOrder(deliverOrders.get(0));
        }
        return customerOrder.getCustomerOrderNo();
    }

    @Override
    public boolean cancelCustomerOrderDetail(String orderDetailNo) {
        boolean canCancel = true;
        CustomerOrderDetail customerOrderDetail = customerOrderDetailRepository.findByOrOrderDetailNo(orderDetailNo);
        if (customerOrderDetail != null) {
            for (DeliverOrder deliverOrder : customerOrderDetail.getDeliverOrders()) {
                if (deliverOrder.getDeliverOrderState() == DeliverOrder.DeliverOrderState.TRANSPORTING ||
                        deliverOrder.getDeliverOrderState() == DeliverOrder.DeliverOrderState.COMPLETE) {
                    canCancel = false;
                    break;
                }
            }
            if (canCancel) {
                customerOrderDetail.setState(CustomerOrderDetail.OrderDetailState.INVALID);
                customerOrderDetail.preUpdate();
                customerOrderDetailRepository.save(customerOrderDetail);
                for (DeliverOrder deliverOrder : customerOrderDetail.getDeliverOrders()) {
                    deliverOrder.setDeliverOrderState(DeliverOrder.DeliverOrderState.CANCEL);
                    deliverOrder.preUpdate();
                    deliverOrderRepository.save(deliverOrder);
                }
                //TODO 退款
            }
        } else {
            throw new BizException(Results.ErrorCode.ORDER_NOT_EXIST);
        }

        return canCancel;
    }

    @Override
    public void startCustomerOrderDetail(String orderDetailNo) {
        CustomerOrderDetail customerOrderDetail = customerOrderDetailRepository.findByOrOrderDetailNo(orderDetailNo);
        customerOrderDetail.setState(CustomerOrderDetail.OrderDetailState.TRANSPORTING);
        customerOrderDetail.preUpdate();
        customerOrderDetailRepository.save(customerOrderDetail);
    }

    @Override
    public void completeCustomerOrderDetail(String orderDetailNo) {
        CustomerOrderDetail customerOrderDetail = customerOrderDetailRepository.findByOrOrderDetailNo(orderDetailNo);
        customerOrderDetail.setState(CustomerOrderDetail.OrderDetailState.COMPLETE);
        customerOrderDetail.preUpdate();
        customerOrderDetailRepository.save(customerOrderDetail);
    }

    @Override
    public Page<OrderResponseVo> queryOrder(QueryOrderRequestVo queryOrderRequestVo, Pageable page) {
        Page domainPage = customerOrderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (queryOrderRequestVo.getCustomerId() != null) {
                //两张表关联查询
                predicate.add(criteriaBuilder.equal(root.join("customer").get("customerId"), queryOrderRequestVo.getCustomerId()));
            }
            if (queryOrderRequestVo.getFrom() != null) {
                if (!StringUtils.isEmpty(queryOrderRequestVo.getFrom().getName())) {
                    predicate.add(criteriaBuilder.equal(root.join("orderDetails", JoinType.LEFT).join("from").get("name"), queryOrderRequestVo.getFrom().getName()));
                }
                if (!StringUtils.isEmpty(queryOrderRequestVo.getFrom().getPhone())) {
                    predicate.add(criteriaBuilder.equal(root.join("orderDetails", JoinType.LEFT).join("from").get("phone"), queryOrderRequestVo.getFrom().getPhone()));
                }
            }
            if (queryOrderRequestVo.getTo() != null) {
                if (!StringUtils.isEmpty(queryOrderRequestVo.getTo().getName())) {
                    predicate.add(criteriaBuilder.equal(root.join("orderDetails", JoinType.LEFT).join("to").get("name"), queryOrderRequestVo.getTo().getName()));
                }
                if (!StringUtils.isEmpty(queryOrderRequestVo.getTo().getPhone())) {
                    predicate.add(criteriaBuilder.equal(root.join("orderDetails", JoinType.LEFT).join("to").get("phone"), queryOrderRequestVo.getTo().getPhone()));
                }
            }
            if (!StringUtils.isEmpty(queryOrderRequestVo.getCustomerOrderNo())) {
                predicate.add(criteriaBuilder.equal(root.get("customerOrderNo"), queryOrderRequestVo.getCustomerOrderNo()));
            }
            if (queryOrderRequestVo.getStartTime() != null) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), queryOrderRequestVo.getStartTime()));
            }
            if (queryOrderRequestVo.getEndTime() != null) {
                predicate.add(criteriaBuilder.lessThan(root.get("createTime").as(Date.class), queryOrderRequestVo.getEndTime()));
            }
            if (queryOrderRequestVo.getPayState() != null) {
                predicate.add(criteriaBuilder.equal(root.get("payState").as(Payment.PayState.class), queryOrderRequestVo.getPayState()));
            }
            if (queryOrderRequestVo.getPayType() != null) {
                predicate.add(criteriaBuilder.equal(root.get("payType").as(Payment.PayType.class), queryOrderRequestVo.getPayType()));
            }

            return criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        }, page);

        Page voPage = domainPage.map((Converter<CustomerOrder, OrderResponseVo>) customerOrder -> {
            OrderResponseVo orderResponseVo = new OrderResponseVo();
            BeanUtils.copyProperties(customerOrder, orderResponseVo);
            return orderResponseVo;
        });
        return voPage;
    }

    private void validate(CreateOrderRequestVo customerOrderVo) {
//        if (customerOrderVo == null
//                || customerOrderVo.getPayType() == null
//                || customerOrderVo.getSource() == null
//                || customerOrderVo.getOrderDetails() == null
//                || customerOrderVo.getOrderDetails().size() == 0) {
//            throw new BizException(Results.ErrorCode.MISSING_PARAMETER);
//        }
    }


}
