package com.tms.controller.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class QueryLocationQuestVo implements Serializable {
    @ApiModelProperty(value = "姓名", name = "name")
    private String name;
    @ApiModelProperty(value = "电话", name = "phone")
    private String phone;
    @ApiModelProperty(value = "省编码", name = "provinceCode")
    private Long provinceCode;
    @ApiModelProperty(value = "市编码", name = "cityCode")
    private Long cityCode;
    @ApiModelProperty(value = "区编码", name = "districtCode")
    private Long districtCode;
    @ApiModelProperty(value = "详细地址", name = "address")
    private String address;
}
