package com.tms.controller;

import com.tms.common.Constant;
import com.tms.common.Results;
import com.tms.controller.vo.request.PostOrderDto;
import com.tms.controller.vo.request.QueryOrderDto;
import com.tms.controller.vo.response.OrderListResponseVo;
import com.tms.controller.vo.response.OrderResponseVo;
import com.tms.controller.vo.response.PaymentResponseVo;
import com.tms.model.CustomerOrder;
import com.tms.model.Payment;
import com.tms.service.CustomerOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.tms.util.PageRequestBuilder.buildPageRequest;

@Api(value = "/orders", description = "用户订单API")
@RestController
@RequestMapping("/api/v1/orders")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;

    @ApiOperation(value = "创建订单", response = Results.class)
    @RequestMapping( method = RequestMethod.POST)
    public Results createOrder(@ApiParam(name = "创建订单参数", value = "传入json格式", required = true) @RequestBody PostOrderDto postOrderDto) {
        if (postOrderDto.getPayment().getPayType() == null)
            postOrderDto.getPayment().setPayType(Constant.PayType.SENDER_PAY);
        Long payId = customerOrderService.createCustomerOrder(postOrderDto);
        return Results.setSuccessMessage(payId);
    }

    @ApiOperation(value = "取消订单", response = Results.class)
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public Results cancelOrder(@ApiParam(name = "订单号", required = true) @RequestBody String customerOrderNo) {
        boolean result = customerOrderService.cancelCustomerOrderDetail(customerOrderNo);
        return Results.setSuccessMessage(result);
    }

    @ApiOperation(value = "完成支付", response = Results.class)
    @RequestMapping(value = "/paid", method = RequestMethod.POST)
    public Results paid(@RequestBody Long paymentId) {
        customerOrderService.paid(paymentId);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "根据用户订单号查询支付信息", response = Results.class)
    @RequestMapping(value = "/paymentId", method = RequestMethod.GET)
    public Results queryPaymentOrderByOrderNo(@RequestBody String customerOrderNo) {
        PaymentResponseVo payment = customerOrderService.queryPaymentOrderByOrderNo(customerOrderNo);
        return Results.setSuccessMessage(payment);
    }

    @ApiOperation(value = "根据支付单好查询合并支付的相关联订单", response = Results.class)
    @RequestMapping(value = "/relation/{paymentId}", method = RequestMethod.POST)
    public Results queryOrderByPaymentId(@PathVariable Long paymentId) {
        List<OrderResponseVo> orders = customerOrderService.queryOrderByPaymentId(paymentId);
        return Results.setSuccessMessage(orders);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Results queryOrder(@PathVariable String id) {
        OrderResponseVo order = customerOrderService.queryOrder(id);
        return Results.setSuccessMessage(order);
    }

    @ApiOperation(value = "查询用户订单", response = Results.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Results queryOrderList(HttpServletRequest request, QueryOrderDto queryOrderDto,
                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) throws ParseException {
        String[] dates = request.getParameterValues("createTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dates != null && dates.length > 0) {
            queryOrderDto.setStartTime(sdf.parse(dates[0]));
            queryOrderDto.setEndTime(sdf.parse(dates[1]));
        }
        Page<OrderListResponseVo> customerOrderPage = customerOrderService.queryCustomerOrder(queryOrderDto, buildPageRequest(page));
        return Results.setSuccessMessage(customerOrderPage);
    }
}
