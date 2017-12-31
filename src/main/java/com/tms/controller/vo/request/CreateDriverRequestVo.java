package com.tms.controller.vo.request;

import com.tms.controller.vo.response.DriverResponseVo;
import com.tms.model.Driver;
import com.tms.model.SysDriver;

public class CreateDriverRequestVo {
    private Long id;
    private String name;
    private Driver.Gender gender;
    private String drivingLicense;
    private String idCard;
    private String education;
    private String bankCard;
    private String phone;
    private SysDriverRequestVo sysDriver;

    public CreateDriverRequestVo() {
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
