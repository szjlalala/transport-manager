package com.tms.controller;

import com.tms.common.Constant;
import com.tms.common.Results;
import com.tms.controller.vo.request.DriverIdPair;
import com.tms.controller.vo.request.VehicleRequestDto;
import com.tms.controller.vo.request.QueryVehicleRequestVo;
import com.tms.controller.vo.request.VehicleTrackRequestDto;
import com.tms.controller.vo.response.TraceResponseVo;
import com.tms.controller.vo.response.VehicleCandidateResponseVo;
import com.tms.controller.vo.response.VehicleResponseVo;
import com.tms.model.AxisPair;
import com.tms.model.Driver;
import com.tms.model.Vehicle;
import com.tms.service.DeliverOrderService;
import com.tms.service.DriverService;
import com.tms.service.VehicleService;
import com.tms.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.tms.util.PageRequestBuilder.buildPageRequest;

@Api(value = "/Vehicle", description = "车辆API")
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    DeliverOrderService deliverOrderService;

    @Autowired
    DriverService driverService;

    @ApiOperation(value = "创建车辆", response = Results.class)
    @RequestMapping(method = RequestMethod.POST)
    public Results createVehicle(@ApiParam(name = "创建车辆参数", value = "传入json格式", required = true) @RequestBody VehicleRequestDto vehicleRequestDto) {
        List<Driver> drivers = new ArrayList<>();
        for(DriverIdPair driverId : vehicleRequestDto.getDrivers()){
            Driver driver = driverService.findDriver(driverId.getId());
            if(driver != null)
                drivers.add(driver);
        }
        vehicleService.createVehicle(vehicleRequestDto, drivers);

        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "更新车辆", response = Results.class)
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Results updateVehicle(@ApiParam(name = "更新车辆参数", value = "传入json格式", required = true) @PathVariable long id, @RequestBody VehicleRequestDto vehicleRequestDto) {
        vehicleRequestDto.setId(id);
        vehicleService.updateVehicle(vehicleRequestDto);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "查询车辆", response = Results.class)
    @RequestMapping( method = RequestMethod.GET)
    public Results queryVehicle(HttpServletRequest request,@ApiParam(name = "查询车辆参数", value = "传入json格式") QueryVehicleRequestVo queryVehicleRequestVo,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) throws ParseException {
        String[] dates = request.getParameterValues("createTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dates != null && dates.length > 0) {
            queryVehicleRequestVo.setStartTime(sdf.parse(dates[0]));
            queryVehicleRequestVo.setEndTime(sdf.parse(dates[1]));
        }
        Page<VehicleResponseVo> voPage = vehicleService.queryVehicle(queryVehicleRequestVo, buildPageRequest(page));
        return Results.setSuccessMessage(voPage);
    }

    @ApiOperation(value = "查询单个车辆", response = Results.class)
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Results queryVehicleById(@PathVariable Long id ){
        VehicleResponseVo vehicleResponseVo = vehicleService.queryVehicle(id);
        return Results.setSuccessMessage(vehicleResponseVo);
    }
    @ApiOperation(value = "添加车辆位置", response = Results.class)
    @RequestMapping(value = "/trace", method = RequestMethod.POST)
    public Results pushTrace(@ApiParam(name = "添加车辆位置", value = "传入json格式", required = true) @RequestBody VehicleTrackRequestDto vehicleTrackRequestDto) {
        vehicleService.pushTrace(vehicleTrackRequestDto);
        return Results.setSuccessMessage(null);
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

    @ApiOperation(value = "根据运单请款选择候选车辆", response = Results.class)
    @RequestMapping(value = "/candidate",method = RequestMethod.GET)
    public Results querySituation(@RequestParam(value = "id") String deliveryNo,  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable page ){
//        之前业务不明确,可以根据delivery的起点建立空间索引然后候选车辆排序

//      目前逻辑,查询现在的在岗车辆
        QueryVehicleRequestVo queryVehicleRequestVo = new QueryVehicleRequestVo();
        queryVehicleRequestVo.setState(Constant.VehicleState.ON);
        Page<VehicleResponseVo> voPage = vehicleService.queryVehicle(queryVehicleRequestVo, buildPageRequest(page));
        //获取车辆行驶轨迹 可设置时间起始点
        Page<VehicleCandidateResponseVo> vos = voPage.map(vehicleResponseVo -> {
            List<TraceResponseVo> traceList = vehicleService.queryTrace(vehicleResponseVo.getId(), null, null);
            List<AxisPair> track = traceList.stream().map(traceResponseVo ->  new AxisPair()).collect(Collectors.toList());
            return new VehicleCandidateResponseVo(vehicleResponseVo, track);
        });
        return Results.setSuccessMessage(vos);
    }


}
