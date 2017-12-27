package com.tms.controller.vo.response;

import com.tms.model.DeliverOrder;
import com.tms.model.Driver;
import com.tms.model.Location;
import com.tms.model.Vehicle;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

public class DeliverOrderResponseVo implements Serializable {
    @ApiModelProperty(value = "运单号", name = "deliverOrderNo")
    private String deliverOrderNo;
    @ApiModelProperty(value = "开始地址", name = "from")
    private LocationResponseVo from;
    @ApiModelProperty(value = "结束地址", name = "to")
    private LocationResponseVo to;
    @ApiModelProperty(value = "开始时间", name = "startTime")
    private Date startTime;
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;
    @ApiModelProperty(value = "司机", name = "driver")
    private DriverResponseVo driver;
    @ApiModelProperty(value = "车辆", name = "vehicle")
    private VehicleResponseVo vehicle;
    @ApiModelProperty(value = "运单状态", name = "deliverOrderState")
    private DeliverOrder.DeliverOrderState deliverOrderState;
    @ApiModelProperty(value = "序列", name = "sequence")
    private Integer sequence;

    public DeliverOrderResponseVo() {
    }

    public String getDeliverOrderNo() {
        return deliverOrderNo;
    }

    public void setDeliverOrderNo(String deliverOrderNo) {
        this.deliverOrderNo = deliverOrderNo;
    }

    public LocationResponseVo getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (from != null) {
            BeanUtils.copyProperties(from, locationResponseVo);
        }
        this.from = locationResponseVo;

    }

    public LocationResponseVo getTo() {
        return to;
    }

    public void setTo(Location to) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (to != null) {
            BeanUtils.copyProperties(to, locationResponseVo);
        }
        this.to = locationResponseVo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public DriverResponseVo getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        DriverResponseVo driverResponseVo = new DriverResponseVo();
        if (driver != null)
            BeanUtils.copyProperties(driver, driverResponseVo);
        this.driver = driverResponseVo;
    }

    public VehicleResponseVo getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        VehicleResponseVo vehicleResponseVo = new VehicleResponseVo();
        if (vehicle != null)
            BeanUtils.copyProperties(vehicle, vehicleResponseVo);
        this.vehicle = vehicleResponseVo;
    }

    public DeliverOrder.DeliverOrderState getDeliverOrderState() {
        return deliverOrderState;
    }

    public void setDeliverOrderState(DeliverOrder.DeliverOrderState deliverOrderState) {
        this.deliverOrderState = deliverOrderState;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
