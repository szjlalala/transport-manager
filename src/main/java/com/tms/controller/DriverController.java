package com.tms.controller;

import com.tms.common.Results;
import com.tms.controller.vo.request.CreateDriverRequestVo;
import com.tms.controller.vo.request.QueryDriverRequestVo;
import com.tms.controller.vo.response.DriverResponseVo;
import com.tms.service.DeliverOrderService;
import com.tms.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.tms.util.PageRequestBuilder.buildPageRequest;

@Api(value = "/Driver", description = "司机API")
@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @Autowired
    DeliverOrderService deliverOrderService;

    @ApiOperation(value = "承接运单", response = Results.class)
    @RequestMapping(value = "/execute", method = RequestMethod.PUT)
    public Results executeDeliverOrder(@ApiParam(name = "订单号", required = true) String customerOrderNo,
                                       @ApiParam(name = "车辆编号", required = true) Long vehicleId,
                                       @ApiParam(name = "司机编号", required = true) Long driverId) {
        deliverOrderService.allocateVehicleAndDriver(customerOrderNo, vehicleId, driverId);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "创建司机", response = Results.class)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Results createVehicle(@ApiParam(name = "创建司机参数", value = "传入json格式", required = true) @RequestBody CreateDriverRequestVo createDriverRequestVo) {
        driverService.createDriver(createDriverRequestVo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "更新司机", response = Results.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Results updateVehicle(@ApiParam(name = "更新司机参数", value = "传入json格式", required = true) @RequestBody CreateDriverRequestVo createDriverRequestVo) {
        driverService.updateDriver(createDriverRequestVo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "查询司机", response = Results.class)
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Results queryVehicle(@ApiParam(name = "查询司机参数", value = "传入json格式") QueryDriverRequestVo queryDriverRequestVo,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        Page<DriverResponseVo> voPage = driverService.queryDriver(queryDriverRequestVo, buildPageRequest(page));
        return Results.setSuccessMessage(voPage);
    }

}
