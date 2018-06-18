package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.CreateVehicleRequestVo;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class Vehicle extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn
    private SysCode vehicleType;//交通工具大类别 airplane vehicle ship
    @OneToOne
    @JoinColumn
    private SysCode vehicleSubType;//子类别 卡车 货车。。。
    private String plateNumber;//牌号
    private String driveLicense;//行驶证
    private String operatorLicense;//运营证
    private Constant.VehicleState state;
    private String brand;//商标
    private String company;//所属公司
    private String owner;//车主
    private String ownerPhone;//车主电话
    private Float loads;//载重量

    public Vehicle(CreateVehicleRequestVo createVehicleRequestVo) {
        BeanUtils.copyProperties(createVehicleRequestVo,this);
//        this.id = createVehicleRequestVo.getId();
//        this.plateNumber = createVehicleRequestVo.getPlateNumber();
//        this.brand = createVehicleRequestVo.getBrand();
//        this.company = createVehicleRequestVo.getCompany();
//        this.driveLicense = createVehicleRequestVo.getDriveLicense();
//        this.loads = createVehicleRequestVo.getLoads();
//        this.operatorLicense = createVehicleRequestVo.getOperatorLicense();
//        this.owner = createVehicleRequestVo.getOwner();
//        this.ownerPhone = createVehicleRequestVo.getOwnerPhone();
        this.state = Constant.VehicleState.OFF;
    }

    public Vehicle() {
    }

    public SysCode getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(SysCode vehicleType) {
        this.vehicleType = vehicleType;
    }

    public SysCode getVehicleSubType() {
        return vehicleSubType;
    }

    public void setVehicleSubType(SysCode vehicleSubType) {
        this.vehicleSubType = vehicleSubType;
    }

    public Float getLoads() {
        return loads;
    }

    public void setLoads(Float loads) {
        this.loads = loads;
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

    public Constant.VehicleState getState() {
        return state;
    }

    public void setState(Constant.VehicleState state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
