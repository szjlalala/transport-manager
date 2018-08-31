package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.DriverRequestDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Data
public class Driver extends BaseModel {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private Constant.Gender gender;
    private String drivingLicense;
    private String idCard;
    private String education;
    private String bankCard;
    private String phone;
    @OneToOne(mappedBy = "driver")
    private SysDriver sysDriver;

    public Driver() {
    }

    public Driver(DriverRequestDto driverRequestDto) {
        BeanUtils.copyProperties(driverRequestDto,this,"gender");
        this.gender = Constant.Gender.getInstance(driverRequestDto.getGender());
    }

}
