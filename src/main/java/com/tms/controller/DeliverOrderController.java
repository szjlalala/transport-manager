package com.tms.controller;

import com.tms.common.Results;
import com.tms.service.DeliverOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/customerOrder", description = "运单API")
@RestController
@RequestMapping("deliverOrder")
public class DeliverOrderController {
    @Autowired
    private DeliverOrderService deliverOrderService;

    @ApiOperation(value = "分配运单", response = Results.class)
    @RequestMapping(value = "/execute", method = RequestMethod.PUT)
    public Results executeDeliverOrder(@ApiParam(name = "订单号", required = true) String orderDetailNo,
                                       @ApiParam(name = "车辆编号", required = true) Long voyageId,
                                       @ApiParam(name = "司机编号", required = true) Long driverId) {
        deliverOrderService.allocateVoyageAndDriver(orderDetailNo, voyageId, driverId);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "开始运单", response = Results.class)
    @RequestMapping(value = "/start/{orderDetailNo}", method = RequestMethod.PUT)
    public Results startOrder(@ApiParam(name = "订单号", required = true) @PathVariable String orderDetailNo) {
        deliverOrderService.startDeliver(orderDetailNo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "完成运单", response = Results.class)
    @RequestMapping(value = "/complete/{orderDetailNo}", method = RequestMethod.PUT)
    public Results completeOrder(@ApiParam(name = "订单号", required = true) @PathVariable String orderDetailNo) {
        deliverOrderService.completeDeliver(orderDetailNo);
        return Results.setSuccessMessage(null);
    }
}
