package com.tms.controller.vo.request;

import com.tms.model.Vehicle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "创建车辆请求参数")
public class CreateVehicleRequestVo implements Serializable {
    @ApiModelProperty(value = "车型", name = "vehicleType")
    private Long vehicleType;
    @ApiModelProperty(value = "品牌", name = "vehicleSubType")
    private Long vehicleSubType;
    @ApiModelProperty(value = "牌号", name = "plateNumber")
    private String plateNumber;
    @ApiModelProperty(value = "行驶证", name = "driveLicense")
    private String driveLicense;
    @ApiModelProperty(value = "运营证", name = "operatorLicense")
    private String operatorLicense;
    @ApiModelProperty(value = "车辆状态", name = "state")
    private Vehicle.VehicleState state;
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

    public CreateVehicleRequestVo() {
    }

    public Long getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Long vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getVehicleSubType() {
        return vehicleSubType;
    }

    public void setVehicleSubType(Long vehicleSubType) {
        this.vehicleSubType = vehicleSubType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriveLicense() {
        return driveLicense;
    }

    public void setDriveLicense(String driveLicense) {
        this.driveLicense = driveLicense;
    }

    public String getOperatorLicense() {
        return operatorLicense;
    }

    public void setOperatorLicense(String operatorLicense) {
        this.operatorLicense = operatorLicense;
    }

    public Vehicle.VehicleState getState() {
        return state;
    }

    public void setState(Vehicle.VehicleState state) {
        this.state = state;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public Float getLoads() {
        return loads;
    }

    public void setLoads(Float loads) {
        this.loads = loads;
    }
}
