package com.tms.service;


import com.tms.controller.vo.request.CreateOrderRequestVo;
import org.springframework.validation.annotation.Validated;

public interface CustomerOrderService {
    String createCustomerOrder(@Validated CreateOrderRequestVo createOrderRequestVo);

    boolean cancelCustomerOrderDetail(String orderDetailNo);

    void startCustomerOrderDetail(String orderDetailNo);

    void completeCustomerOrderDetail(String orderDetailNo);
}
