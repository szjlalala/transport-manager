package com.tms.controller;

import com.tms.common.Results;
import com.tms.controller.vo.request.QueryDeliverOrderRequestVo;
import com.tms.controller.vo.request.QueryOrderRequestVo;
import com.tms.controller.vo.response.DeliverOrderResponseVo;
import com.tms.service.DeliverOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Results executeDeliverOrder(@ApiParam(name = "订单号", required = true) String customerOrderNo,
                                       @ApiParam(name = "车辆编号", required = true) Long vehicleId,
                                       @ApiParam(name = "司机编号", required = true) Long driverId) {
        deliverOrderService.allocateVehicleAndDriver(customerOrderNo, vehicleId, driverId);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "开始运单", response = Results.class)
    @RequestMapping(value = "/start/{customerOrderNo}", method = RequestMethod.PUT)
    public Results startOrder(@ApiParam(name = "订单号", required = true) @PathVariable String customerOrderNo) {
        deliverOrderService.startDeliver(customerOrderNo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "完成运单", response = Results.class)
    @RequestMapping(value = "/complete/{customerOrderNo}", method = RequestMethod.PUT)
    public Results completeOrder(@ApiParam(name = "订单号", required = true) @PathVariable String customerOrderNo) {
        deliverOrderService.completeDeliver(customerOrderNo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "查询运单", response = Results.class)
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Results queryOrder(QueryDeliverOrderRequestVo deliverOrderRequestVo,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        Page<DeliverOrderResponseVo> responseVoPage =  deliverOrderService.queryDeliverOrder(deliverOrderRequestVo,page);
        return Results.setSuccessMessage(responseVoPage);
    }
}
