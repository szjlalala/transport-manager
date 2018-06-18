package com.tms.controller.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LocationResponseVo implements Serializable {
    @ApiModelProperty(value = "姓名", name = "name")
    private String name;
    @ApiModelProperty(value = "电话", name = "phone")
    private String phone;
    @ApiModelProperty(value = "区编码", name = "district")
    private Long district;
    @ApiModelProperty(value = "详细地址", name = "address")
    private String address;
    @ApiModelProperty(value = "X坐标", name = "x")
    private Double x;
    @ApiModelProperty(value = "Y坐标", name = "y")
    private Double y;

}
