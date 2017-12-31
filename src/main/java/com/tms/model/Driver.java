package com.tms.model;

import com.tms.controller.vo.request.CreateDriverRequestVo;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class Driver extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Gender gender;
    private String drivingLicense;
    private String idCard;
    private String education;
    private String bankCard;
    private String phone;
    @OneToOne(mappedBy = "driver")
    private SysDriver sysDriver;

    public Driver() {
    }

    public Driver(CreateDriverRequestVo createDriverRequestVo) {
        BeanUtils.copyProperties(createDriverRequestVo,this);
    }

    public enum Gender {
        MALE, FEMALE
    }


    public SysDriver getSysDriver() {
        return sysDriver;
    }

    public void setSysDriver(SysDriver sysDriver) {
        this.sysDriver = sysDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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
