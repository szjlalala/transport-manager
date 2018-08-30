package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.model.Driver;
import com.tms.model.Vehicle;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Created by song on 2018/8/30.
 */
@Data
public class VehicleCandidateResponseVo {
    @ApiModelProperty(value = "id", name = "id")
    private Long id;
    @ApiModelProperty(value = "车型", name = "vehicleType")
    private String vehicleType;
    @ApiModelProperty(value = "牌号", name = "plateNumber")
    private String plateNumber;
    @ApiModelProperty(value = "行驶证", name = "driveLicense")
    private String driveLicense;
    @ApiModelProperty(value = "运营证", name = "operatorLicense")
    private String operatorLicense;
    @ApiModelProperty(value = "车辆状态", name = "state")
    private Constant.VehicleState state;
    @ApiModelProperty(value = "商标", name = "brand")
    private String brand;
    @ApiModelProperty(value = "所属公司", name = "company")
    private String company;
    @ApiModelProperty(value = "车主", name = "owner")
    private String owner;
    @ApiModelProperty(value = "车主电话", name = "ownerPhone")
    private String ownerPhone;
    @ApiModelProperty(value = "载重量", name = "loads")
    private Float loads;
    @ApiModelProperty(value = "当班司机", name = "driver")
    private DriverResponseVo driver;

    //TODO
    public VehicleCandidateResponseVo(Vehicle vehicle) {
        BeanUtils.copyProperties(vehicle,this);
//        this.setDrivers(vehicle.getDriverList());
    }
}