package com.tms.controller.vo.request;

import com.tms.common.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel(value = "创建司机请求参数")
public class CreateDriverRequestVo implements Serializable {
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

}
