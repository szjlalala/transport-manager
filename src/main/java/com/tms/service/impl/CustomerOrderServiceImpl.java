package com.tms.service.impl;


import com.tms.common.BizException;
import com.tms.common.Constant;
import com.tms.common.Results;
import com.tms.controller.vo.request.PostOrderDto;
import com.tms.controller.vo.request.QueryOrderDto;
import com.tms.controller.vo.request.QueryOrderRequestVo;
import com.tms.controller.vo.response.OrderListResponseVo;
import com.tms.controller.vo.response.OrderResponseVo;
import com.tms.controller.vo.response.PaymentResponseVo;
import com.tms.model.CustomerOrder;
import com.tms.model.DeliverOrder;
import com.tms.model.Payment;
import com.tms.model.PaymentItem;
import com.tms.repository.CustomerOrderRepository;
import com.tms.repository.DeliverOrderRepository;
import com.tms.repository.PaymentRepository;
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

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private DeliverOrderService deliverOrderService;
    @Autowired
    private SysCodeRepository sysCodeRepository;
    @Autowired
    private DeliverOrderRepository deliverOrderRepository;
    @Autowired
    private MQProducer mqProducer;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public Long createCustomerOrder(PostOrderDto postOrderDto) {
        //验证
        validate(postOrderDto);
        //生成用户订单
        Payment payment = new Payment(postOrderDto);
        payment = paymentRepository.save(payment);
        //生成运单
        for (CustomerOrder customerOrder : payment.getCustomerOrders()) {
            deliverOrderService.createDeliverOrder(customerOrder);
        }
        //将运单广播
        for (CustomerOrder customerOrder : payment.getCustomerOrders()) {
            List<DeliverOrder> deliverOrders = deliverOrderRepository.findByCustomerOrderAndDeliverOrderStateOrderBySequenceAsc(customerOrder, Constant.OrderState.NOT_DISTRIBUTED);
            if(deliverOrders!=null && deliverOrders.size()>0){
                deliverOrderService.spreadDeliverOrder(deliverOrders.get(0));
            }
        }
        return payment.getId();
    }

    @Override
    public boolean cancelCustomerOrderDetail(String customerOrderNo) {
        boolean canCancel = true;
        CustomerOrder customerOrder = customerOrderRepository.findByCustomerOrderNo(customerOrderNo);
        if (customerOrder != null) {
            for (DeliverOrder deliverOrder : customerOrder.getDeliverOrders()) {
                if (deliverOrder.getDeliverOrderState() == Constant.OrderState.ONBOARD ||
                        deliverOrder.getDeliverOrderState() == Constant.OrderState.COMPLETED) {
                    canCancel = false;
                    break;
                }
            }
            if (canCancel) {
                Payment payment = customerOrder.getPayment();
                for (CustomerOrder order : payment.getCustomerOrders()) {
                    order.setState(Constant.OrderState.INVALID);
                    order.preUpdate();
                    customerOrderRepository.save(order);
                    for (DeliverOrder deliverOrder : order.getDeliverOrders()) {
                        deliverOrder.setDeliverOrderState(Constant.OrderState.INVALID);
                        deliverOrder.preUpdate();
                        deliverOrderRepository.save(deliverOrder);
                    }
                }


                //TODO 退款
            }
        } else {
            throw new BizException(Results.ErrorCode.ORDER_NOT_EXIST);
        }

        return canCancel;
    }

    @Override
    public void startCustomerOrderDetail(String customerOrderNo) {
        changeCustomerOrderStatus(customerOrderNo, Constant.OrderState.NOT_RECEIVED);
    }

    @Override
    public void undergoCustomerOrderDetail(String customerOrderNo) {
        changeCustomerOrderStatus(customerOrderNo, Constant.OrderState.ONBOARD);
    }

    @Override
    public void completeCustomerOrderDetail(String customerOrderNo) {
        changeCustomerOrderStatus(customerOrderNo, Constant.OrderState.COMPLETED);
    }

    public void changeCustomerOrderStatus(String customerOrderNo, Constant.OrderState orderState) {
        CustomerOrder customerOrder = customerOrderRepository.findByCustomerOrderNo(customerOrderNo);
        customerOrder.setState(orderState);
        customerOrder.preUpdate();
        customerOrderRepository.save(customerOrder);
    }


    @Override
    public Page<PaymentResponseVo> queryOrder(QueryOrderRequestVo queryOrderRequestVo, Pageable page) {
        Page domainPage = customerOrderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            return criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        }, page);

        Page voPage = domainPage.map((Converter<Payment, PaymentResponseVo>) customerOrder -> {
            PaymentResponseVo paymentResponseVo = new PaymentResponseVo();
            BeanUtils.copyProperties(customerOrder, paymentResponseVo);
            return paymentResponseVo;
        });
        return voPage;
    }

    @Override
    public Page<OrderListResponseVo> queryCustomerOrder(QueryOrderDto queryOrderDto, Pageable page) {
        Page domainPage = customerOrderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (queryOrderDto.getCustomerId() != null) {
                //两张表关联查询
                predicate.add(criteriaBuilder.equal(root.join("customer").get("customerId"), queryOrderDto.getCustomerId()));
            }
            if (queryOrderDto.getState() != null) {
                //两张表关联查询
                predicate.add(criteriaBuilder.equal(root.get("state").as(Constant.OrderState.class), queryOrderDto.getState()));
            }
            if (queryOrderDto.getFrom() != null) {
                if (!StringUtils.isEmpty(queryOrderDto.getFrom().getName())) {
                    predicate.add(criteriaBuilder.like(root.join("from").get("name"), "%" + queryOrderDto.getFrom().getName() + "%"));
                }
                if (!StringUtils.isEmpty(queryOrderDto.getFrom().getPhone())) {
                    predicate.add(criteriaBuilder.equal(root.join("from").get("phone"), queryOrderDto.getFrom().getPhone()));
                }
                if (!StringUtils.isEmpty(queryOrderDto.getFrom().getAddress())) {
                    predicate.add(criteriaBuilder.like(root.join("from").get("address"), "%" + queryOrderDto.getFrom().getAddress() + "%"));
                }
            }
            if (queryOrderDto.getTo() != null) {
                if (!StringUtils.isEmpty(queryOrderDto.getTo().getName())) {
                    predicate.add(criteriaBuilder.like(root.join("to").get("name"), "%" + queryOrderDto.getTo().getName() + "%"));
                }
                if (!StringUtils.isEmpty(queryOrderDto.getTo().getPhone())) {
                    predicate.add(criteriaBuilder.equal(root.join("to").get("phone"), queryOrderDto.getTo().getPhone()));
                }
                if (!StringUtils.isEmpty(queryOrderDto.getTo().getAddress())) {
                    predicate.add(criteriaBuilder.like(root.join("to").get("address"), "%" + queryOrderDto.getTo().getAddress() + "%"));
                }
            }
            if (!StringUtils.isEmpty(queryOrderDto.getId())) {
                predicate.add(criteriaBuilder.equal(root.get("customerOrderNo"), queryOrderDto.getId()));
            }
            if (queryOrderDto.getStartTime() != null) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), queryOrderDto.getStartTime()));
            }
            if (queryOrderDto.getEndTime() != null) {
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), queryOrderDto.getEndTime()));
            }
            if (queryOrderDto.getPayState() != null) {
                predicate.add(criteriaBuilder.equal(root.join("payment").get("payState").as(Constant.PayState.class), queryOrderDto.getPayState()));
            }
            if (queryOrderDto.getPayType() != null) {
                predicate.add(criteriaBuilder.equal(root.join("payment").get("payType").as(Constant.PayType.class), queryOrderDto.getPayType()));
            }

            return criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        }, page);

        Page voPage = domainPage.map((Converter<CustomerOrder, OrderListResponseVo>) customerOrder -> new OrderListResponseVo(customerOrder));
        return voPage;
    }

    @Override
    public OrderResponseVo queryOrder(String id) {
        CustomerOrder customerOrder = customerOrderRepository.findByCustomerOrderNo(id);
        OrderResponseVo orderResponseVo = new OrderResponseVo(customerOrder);
        return orderResponseVo;
    }

    @Override
    public void paid(Long id) {
        Payment payment = paymentRepository.findOne(id);
        if (payment != null) {
            payment.setPayState(Constant.PayState.COMPLETE);
            //TODO 目前写死，无支付配置
            PaymentItem paymentItem = new PaymentItem();
            paymentItem.setFinishTime(new Date());
            paymentItem.setPayChannel(Constant.PayChannel.ALIPAY);
            paymentItem.setPayment(payment);
            paymentItem.setPayPrice(payment.getPayPrice());
            paymentItem.setPayState(Constant.PayState.COMPLETE);
            paymentItem.setTradeNo("1234");
            for (CustomerOrder customerOrder : payment.getCustomerOrders()) {
                customerOrder.setState(payment.getPayType().equals(Constant.PayType.SENDER_PAY) ? Constant.OrderState.NOT_DISTRIBUTED : Constant.OrderState.COMPLETED);
                customerOrderRepository.save(customerOrder);
                for (DeliverOrder deliverOrder : customerOrder.getDeliverOrders()) {
                    deliverOrder.setDeliverOrderState(payment.getPayType().equals(Constant.PayType.SENDER_PAY) ? Constant.OrderState.NOT_DISTRIBUTED : Constant.OrderState.COMPLETED);
                }
            }
        }
    }

    @Override
    public List<OrderResponseVo> queryOrderByPaymentId(Long id) {
        Payment payment = paymentRepository.findOne(id);
        List<OrderResponseVo> result = new ArrayList<>();
        for (CustomerOrder customerOrder : payment.getCustomerOrders()) {
            result.add(new OrderResponseVo(customerOrder));
        }
        return result;
    }

    @Override
    public PaymentResponseVo queryPaymentOrderByOrderNo(String customerOrderNo) {
        CustomerOrder customerOrder = customerOrderRepository.findByCustomerOrderNo(customerOrderNo);
        return new PaymentResponseVo(customerOrder.getPayment());
    }

    private void validate(PostOrderDto postOrderDto) {
        //TODO
    }


}
