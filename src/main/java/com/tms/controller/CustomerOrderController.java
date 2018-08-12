package com.tms.controller;

import com.tms.common.Constant;
import com.tms.common.Results;
import com.tms.controller.vo.request.PostOrderDto;
import com.tms.controller.vo.request.QueryOrderDto;
import com.tms.controller.vo.response.OrderListResponseVo;
import com.tms.controller.vo.response.OrderResponseVo;
import com.tms.service.CustomerOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(value = "/orders", description = "用户订单API")
@RestController
@RequestMapping("/api/v1")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;

    @ApiOperation(value = "创建订单", response = Results.class)
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Results createOrder(@ApiParam(name = "创建订单参数", value = "传入json格式", required = true) @RequestBody PostOrderDto postOrderDto) {
        if(postOrderDto.getPayment().getPayType() == null)
            postOrderDto.getPayment().setPayType(Constant.PayType.SENDER_PAY);
        Long payId = customerOrderService.createCustomerOrder(postOrderDto);
        return Results.setSuccessMessage(payId);
    }

    @ApiOperation(value = "取消订单", response = Results.class)
    @RequestMapping(value = "/cancel/{customerOrderNo}", method = RequestMethod.PUT)
    public Results cancelOrder(@ApiParam(name = "订单号", required = true) @PathVariable String customerOrderNo) {
        boolean result = customerOrderService.cancelCustomerOrderDetail(customerOrderNo);
        return Results.setSuccessMessage(result);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public Results queryOrder( @PathVariable String id) {
        OrderResponseVo order = customerOrderService.queryOrder(id);
        return Results.setSuccessMessage(order);
    }

    @ApiOperation(value = "查询用户订单", response = Results.class)
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Results queryOrderList(@RequestBody QueryOrderDto queryOrderDto,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        Page<OrderListResponseVo> customerOrderPage = customerOrderService.queryCustomerOrder(queryOrderDto, page);
        return Results.setSuccessMessage(customerOrderPage);
    }
}
