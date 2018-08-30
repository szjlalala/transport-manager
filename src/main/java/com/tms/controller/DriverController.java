package com.tms.controller;

import com.tms.common.Results;
import com.tms.controller.vo.request.DriverRequestDto;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
                                       @ApiParam(name = "司机编号", required = true) String driverId) {
        deliverOrderService.allocateVehicleAndDriver(customerOrderNo, vehicleId, driverId);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "创建司机", response = Results.class)
    @RequestMapping(method = RequestMethod.POST)
    public Results createDriver(@ApiParam(name = "创建司机参数", value = "传入json格式", required = true) @RequestBody DriverRequestDto driverRequestDto) {
        driverService.createDriver(driverRequestDto);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "更新司机", response = Results.class)
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Results updateDriver(@ApiParam(name = "更新司机参数", value = "传入json格式", required = true)@PathVariable String id, @RequestBody DriverRequestDto driverRequestDto) {
        driverRequestDto.setId(id);
        driverService.updateDriver(driverRequestDto);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "根据id查询司机", response = Results.class)
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Results queryDriverById(@PathVariable String id) {
        return Results.setSuccessMessage(driverService.queryDriver(id));
    }
    @ApiOperation(value = "查询司机", response = Results.class)
    @RequestMapping(method = RequestMethod.GET)
    public Results queryDriver(HttpServletRequest request,@ApiParam(name = "查询司机参数", value = "传入json格式") QueryDriverRequestVo queryDriverRequestVo,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) throws ParseException {
        String[] dates = request.getParameterValues("createTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dates != null && dates.length > 0) {
            queryDriverRequestVo.setStartTime(sdf.parse(dates[0]));
            queryDriverRequestVo.setEndTime(sdf.parse(dates[1]));
        }
        Page<DriverResponseVo> voPage = driverService.queryDriver(queryDriverRequestVo, buildPageRequest(page));
        return Results.setSuccessMessage(voPage);
    }

}
