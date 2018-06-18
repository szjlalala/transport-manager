package com.tms.controller.vo.response;

import com.tms.common.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class DriverResponseVo implements Serializable {
    @ApiModelProperty(value = "司机id", name = "id")
    private Long id;
    @ApiModelProperty(value = "姓名", name = "name", required = true)
    private String name;
    @ApiModelProperty(value = "性别", name = "gender", required = true)
    private Constant.Gender gender;
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

}
