package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.VehicleRequestDto;
import com.tms.service.DriverService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Vehicle extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*@OneToOne
    @JoinColumn
    private SysCode vehicleType;//交通工具大类别 airplane vehicle ship*/
    /*@OneToOne
    @JoinColumn
    private SysCode vehicleSubType;//子类别 卡车 货车。。。*/
    private String vehicleType;
    private String plateNumber;//牌号
    private String driveLicense;//行驶证
    private String operatorLicense;//运营证
    private Constant.VehicleState state = Constant.VehicleState.ON;
    private String brand;//商标
    private String company;//所属公司
    private String owner;//车主
    private String ownerPhone;//车主电话
    private Float loads;//载重量
    private Float remainLoads;//剩余载重
    @OneToMany
    private List<Driver> driverList;

    public Vehicle(VehicleRequestDto vehicleRequestDto) {
        BeanUtils.copyProperties(vehicleRequestDto,this,"id");
    }

}
