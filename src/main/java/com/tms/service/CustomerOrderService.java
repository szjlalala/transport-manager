package com.tms.service;


import com.tms.controller.vo.CustomerOrderVo;

public interface CustomerOrderService {
    String createCustomerOrder(CustomerOrderVo customerOrderVo);

    void cancelCustomerOrderDetail(String orderDetailNo);

    void startCustomerOrderDetail(String orderDetailNo);

    void completeCustomerOrderDetail(String orderDetailNo);
}
