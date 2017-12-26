package com.tms.service;


import com.tms.controller.vo.request.CreateOrderRequestVo;
import com.tms.controller.vo.request.QueryOrderRequestVo;
import com.tms.model.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

public interface CustomerOrderService {
    String createCustomerOrder(@Validated CreateOrderRequestVo createOrderRequestVo);

    boolean cancelCustomerOrderDetail(String orderDetailNo);

    void startCustomerOrderDetail(String orderDetailNo);

    void completeCustomerOrderDetail(String orderDetailNo);

    Page<CustomerOrder> queryOrder(QueryOrderRequestVo queryOrderRequestVo, Pageable page);
}
