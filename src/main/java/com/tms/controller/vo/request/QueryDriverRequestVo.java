package com.tms.controller.vo.request;

import com.tms.common.Constant;
import com.tms.model.Driver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@ApiModel(value = "查询司机请求参数")
public class QueryDriverRequestVo implements Serializable {
    @ApiModelProperty(value = "姓名", name = "id")
    private Long id;
    @ApiModelProperty(value = "姓名", name = "name")
    private String name;
    @ApiModelProperty(value = "性别", name = "gender")
    private Constant.Gender gender;
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
    @ApiModelProperty(value = "司机状态", name = "status")
    private Constant.DriverState status;
    @ApiModelProperty(value = "开始时间", name = "startTime")
    private Date startTime;
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;
}
