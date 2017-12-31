package com.tms.controller.vo.request;

import com.tms.model.Vehicle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "创建订单详情请求参数")
public class CreateVehicleRequestVo implements Serializable {
    @ApiModelProperty(value = "用户备注", name = "customerRemark")
    private Long vehicleType;//交通工具大类别 airplane vehicle ship
    private Long vehicleSubType;//子类别 卡车 货车。。。
    private String plateNumber;//牌号
    private String driveLicense;//行驶证
    private String operatorLicense;//运营证
    private Vehicle.VehicleState state;
    private String brand;//商标
    private String company;//所属公司
    private String owner;//车主
    private String ownerPhone;//车主电话
    private Float loads;//载重量

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
