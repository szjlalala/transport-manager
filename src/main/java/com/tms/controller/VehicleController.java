package com.tms.controller;

import com.tms.common.Results;
import com.tms.controller.vo.request.CreateVehicleRequestVo;
import com.tms.controller.vo.request.QueryVehicleRequestVo;
import com.tms.controller.vo.response.TraceResponseVo;
import com.tms.controller.vo.response.VehicleResponseVo;
import com.tms.service.DeliverOrderService;
import com.tms.service.VehicleService;
import com.tms.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "/Vehicle", description = "车辆API")
@RestController
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    DeliverOrderService deliverOrderService;

    @ApiOperation(value = "创建车辆", response = Results.class)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Results createVehicle(@ApiParam(name = "创建车辆参数", value = "传入json格式", required = true) @RequestBody CreateVehicleRequestVo createVehicleRequestVo) {
        vehicleService.createVehicle(createVehicleRequestVo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "更新车辆", response = Results.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Results updateVehicle(@ApiParam(name = "更新车辆参数", value = "传入json格式", required = true) @RequestBody CreateVehicleRequestVo createVehicleRequestVo) {
        vehicleService.updateVehicle(createVehicleRequestVo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "查询车辆", response = Results.class)
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Results queryVehicle(@ApiParam(name = "查询车辆参数", value = "传入json格式") QueryVehicleRequestVo queryVehicleRequestVo,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        Page<VehicleResponseVo> voPage = vehicleService.queryVehicle(queryVehicleRequestVo, page);
        return Results.setSuccessMessage(voPage);
    }

    @ApiOperation(value = "查询车辆轨迹", response = Results.class)
    @RequestMapping(value = "/trace/{vehicleId}", method = RequestMethod.GET)
    public Results queryTrace(@ApiParam(name = "车辆Id") @PathVariable Long vehicleId, Date start, Date end) {
        List<TraceResponseVo> traceList = vehicleService.queryTrace(
                vehicleId,
                start == null ? new Date(DateUtil.getTimesMonthMorning()) : start,
                end == null ? new Date() : end);
        return Results.setSuccessMessage(traceList);
    }


}
