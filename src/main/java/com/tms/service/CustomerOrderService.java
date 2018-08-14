package com.tms.service;


import com.tms.controller.vo.request.PostOrderDto;
import com.tms.controller.vo.request.QueryOrderDto;
import com.tms.controller.vo.request.QueryOrderRequestVo;
import com.tms.controller.vo.response.OrderListResponseVo;
import com.tms.controller.vo.response.OrderResponseVo;
import com.tms.controller.vo.response.PaymentResponseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

public interface CustomerOrderService {
    Long createCustomerOrder(@Validated PostOrderDto postOrderDto);

    boolean cancelCustomerOrderDetail(String customerOrderNo);

    void startCustomerOrderDetail(String customerOrderNo);

    void completeCustomerOrderDetail(String customerOrderNo);

    void undergoCustomerOrderDetail(String customerOrderNo);

    Page<PaymentResponseVo> queryOrder(QueryOrderRequestVo queryOrderRequestVo, Pageable page);

    Page<OrderListResponseVo> queryCustomerOrder(QueryOrderDto queryOrderDto, Pageable page);


    OrderResponseVo queryOrder(String id);
}
