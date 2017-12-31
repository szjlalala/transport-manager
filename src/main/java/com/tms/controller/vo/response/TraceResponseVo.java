package com.tms.controller.vo.response;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.geo.Point;

import java.io.Serializable;

public class TraceResponseVo implements Serializable {
    @ApiModelProperty(value = "坐标", name = "geo")
    private Point geo;
    @ApiModelProperty(value = "车辆", name = "vehicle")
    private VehicleResponseVo vehicle;
    @ApiModelProperty(value = "司机", name = "driver")
    private DriverResponseVo driver;

    public TraceResponseVo() {
    }

    public Point getGeo() {
        return geo;
    }

    public void setGeo(Point geo) {
        this.geo = geo;
    }

    public VehicleResponseVo getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleResponseVo vehicle) {
        this.vehicle = vehicle;
    }

    public DriverResponseVo getDriver() {
        return driver;
    }

    public void setDriver(DriverResponseVo driver) {
        this.driver = driver;
    }
}
