package com.tms.controller;

import com.tms.common.Results;
import com.tms.controller.vo.CustomerOrderVo;
import com.tms.model.DeliverOrder;
import com.tms.service.CustomerOrderService;
import com.tms.service.DeliverOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deliverOrder")
public class DeliverOrderController {
    @Autowired
    private DeliverOrderService deliverOrderService;

    @RequestMapping("/execute")
    public Results executeDeliverOrder(String orderDetailNo,Long voyageId,Long driverId){
        deliverOrderService.allocateVoyageAndDriver(orderDetailNo,voyageId,driverId);
        return Results.setSuccessMessage(null);
    }

    @RequestMapping("/start")
    public Results startOrder(String orderDetailNo){
        deliverOrderService.startDeliver(orderDetailNo);
        return Results.setSuccessMessage(null);
    }

    @RequestMapping("/complete")
    public Results completeOrder(String orderDetailNo){
        deliverOrderService.completeDeliver(orderDetailNo);
        return Results.setSuccessMessage(null);
    }
}
