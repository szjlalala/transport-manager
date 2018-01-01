package com.tms.controller.vo.response;

import com.tms.model.Driver;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class DriverResponseVo implements Serializable {
    @ApiModelProperty(value = "司机id", name = "id")
    private Long id;
    @ApiModelProperty(value = "姓名", name = "name", required = true)
    private String name;
    @ApiModelProperty(value = "性别", name = "gender", required = true)
    private Driver.Gender gender;
    @ApiModelProperty(value = "驾驶证", name = "drivingLicense", required = true)
    private String drivingLicense;
    @ApiModelProperty(value = "身份证", name = "idCard", required = true)
    private String idCard;
    @ApiModelProperty(value = "学历", name = "education", required = true)
    private String education;
    @ApiModelProperty(value = "银行卡", name = "bankCard", required = true)
    private String bankCard;
    @ApiModelProperty(value = "电话", name = "phone", required = true)
    private String phone;
    @ApiModelProperty(value = "司机状态", name = "sysDriver")
    private SysDriverResponseVo sysDriver;

    public DriverResponseVo() {
    }

    public String getName() {
        return name;
    }

    public SysDriverResponseVo getSysDriver() {
        return sysDriver;
    }

    public void setSysDriver(SysDriverResponseVo sysDriver) {
        this.sysDriver = sysDriver;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Driver.Gender getGender() {
        return gender;
    }

    public void setGender(Driver.Gender gender) {
        this.gender = gender;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
