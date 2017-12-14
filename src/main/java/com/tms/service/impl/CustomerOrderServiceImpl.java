package com.tms.service.impl;


import com.tms.common.BizException;
import com.tms.common.Results;
import com.tms.controller.vo.CustomerOrderVo;
import com.tms.model.CustomerOrder;
import com.tms.model.CustomerOrderDetail;
import com.tms.model.DeliverOrder;
import com.tms.repository.CustomerOrderDetailRepository;
import com.tms.repository.CustomerOrderRepository;
import com.tms.repository.DeliverOrderRepository;
import com.tms.repository.SysCodeRepository;
import com.tms.service.CustomerOrderService;
import com.tms.service.DeliverOrderService;
import com.tms.service.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String createCustomerOrder(CustomerOrderVo customerOrderVo) {
        //验证
        validate(customerOrderVo);
        //生成用户订单
        CustomerOrder customerOrder = new CustomerOrder(customerOrderVo);
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
    public void cancelCustomerOrderDetail(String orderDetailNo) {
        boolean canCancel = true;
        CustomerOrderDetail customerOrderDetail = customerOrderDetailRepository.findByOrOrderDetailNo(orderDetailNo);
        for (DeliverOrder deliverOrder : customerOrderDetail.getDeliverOrders()) {
            if (deliverOrder.getDeliverOrderState() != DeliverOrder.DeliverOrderState.UNALLOCATED) {
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


    private void validate(CustomerOrderVo customerOrderVo) {
        if (customerOrderVo == null
                || customerOrderVo.getPayType() == null
                || customerOrderVo.getSource() == null
                || customerOrderVo.getOrderDetails() == null
                || customerOrderVo.getOrderDetails().size() == 0) {
            throw new BizException(Results.ErrorCode.MISSING_PARAMETER);
        }
    }


}
