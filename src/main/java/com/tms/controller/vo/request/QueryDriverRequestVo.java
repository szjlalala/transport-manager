package com.tms.controller.vo.request;

import com.tms.model.Driver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "查询司机请求参数")
public class QueryDriverRequestVo implements Serializable {
    @ApiModelProperty(value = "姓名", name = "id")
    private Long id;
    @ApiModelProperty(value = "姓名", name = "name")
    private String name;
    @ApiModelProperty(value = "性别", name = "gender")
    private Driver.Gender gender;
    @ApiModelProperty(value = "驾驶证", name = "drivingLicense")
    private String drivingLicense;
    @ApiModelProperty(value = "身份证", name = "idCard")
    private String idCard;
    @ApiModelProperty(value = "学历", name = "education")
    private String education;
    @ApiModelProperty(value = "银行卡", name = "bankCard")
    private String bankCard;
    @ApiModelProperty(value = "电话", name = "phone")
    private String phone;
    @ApiModelProperty(value = "司机状态", name = "sysDriver")
    private SysDriverRequestVo sysDriver;
    @ApiModelProperty(value = "开始时间", name = "startTime")
    private Date startTime;
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;

    public QueryDriverRequestVo() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public SysDriverRequestVo getSysDriver() {
        return sysDriver;
    }

    public void setSysDriver(SysDriverRequestVo sysDriver) {
        this.sysDriver = sysDriver;
    }
}
