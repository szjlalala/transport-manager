package com.tms.controller;

import com.tms.common.PageParam;
import com.tms.common.Results;
import com.tms.controller.vo.request.QueryDeliverOrderRequestVo;
import com.tms.controller.vo.request.SplitDto;
import com.tms.controller.vo.response.DeliverOrderResponseVo;
import com.tms.controller.vo.response.PageWrapper;
import com.tms.service.DeliverOrderService;
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

@Api(value = "/api/v1/deliveries", description = "运单API")
@RestController
@CrossOrigin
@RequestMapping("/api/v1/deliveries")
public class DeliverOrderController {
    @Autowired
    private DeliverOrderService deliverOrderService;

    @ApiOperation(value = "分配运单", response = Results.class)
    @RequestMapping(value = "/assignTo", method = RequestMethod.POST)
    public Results executeDeliverOrder(@ApiParam(name = "运单号", required = true) String deliverOrderNo,
                                       @ApiParam(name = "车辆编号", required = true) Long vehicleId,
                                       @ApiParam(name = "司机编号", required = true) String driverId) {
        deliverOrderService.allocateVehicleAndDriver(deliverOrderNo, vehicleId, driverId);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "开始运单,即确认接货", response = Results.class)
    @RequestMapping(value = "/start/{deliverOrderNo}", method = RequestMethod.PUT)
    public Results startOrder(@PathVariable String deliverOrderNo) {
        deliverOrderService.startDeliver(deliverOrderNo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "完成运单", response = Results.class)
    @RequestMapping(value = "/complete/{deliverOrderNo}", method = RequestMethod.PUT)
    public Results completeOrder(@PathVariable String deliverOrderNo) {
        deliverOrderService.completeDeliver(deliverOrderNo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "用户确认", response = Results.class)
    @RequestMapping(value = "/confirm/{deliverOrderNo}", method = RequestMethod.PUT)
    public Results confirmOrder(@PathVariable String deliverOrderNo) {
        deliverOrderService.confirmDeliver(deliverOrderNo);
        return Results.setSuccessMessage(null);
    }

    @ApiOperation(value = "运单详情页空数据处理", response = Results.class)
    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public Results dead() {
        return Results.setSuccessMessage("");
    }

    @ApiOperation(value = "查询运单", response = Results.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Results queryOrder(HttpServletRequest request, QueryDeliverOrderRequestVo deliverOrderRequestVo,
                              @RequestParam(required = false,defaultValue = "0") int page, @RequestParam(required = false,defaultValue = "0") int pageSize) throws ParseException {
        String[] dates = request.getParameterValues("createTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dates != null && dates.length > 0) {
            deliverOrderRequestVo.setStartTime(sdf.parse(dates[0]));
            deliverOrderRequestVo.setEndTime(sdf.parse(dates[1]));
        }
        Page<DeliverOrderResponseVo> responseVoPage = deliverOrderService.queryDeliverOrder(deliverOrderRequestVo, buildPageRequest(new PageParam(page, pageSize)));
        return Results.setSuccessMessage(new PageWrapper(responseVoPage));
    }

    @ApiOperation(value = "根据Number查询运单", response = Results.class)
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public Results queryOrder(@PathVariable String number) {
        DeliverOrderResponseVo vo = new DeliverOrderResponseVo(deliverOrderService.queryDeliverOrderByNo(number));
        return Results.setSuccessMessage(vo);
    }

    @ApiOperation(value = "拆分", response = Results.class)
    @RequestMapping(value = "/split", method = RequestMethod.POST)
    public Results split(@RequestBody SplitDto splitDto) {
        deliverOrderService.split(splitDto);
        return Results.setSuccessMessage("");
    }

}
