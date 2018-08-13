package com.tms.controller.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/8/12.
 */
@Data
@NoArgsConstructor
public class Address {
    @ApiModelProperty(value = "详细地址", name = "str", required = true)
    private String str;
    @ApiModelProperty(value = "X坐标", name = "x", required = true)
    private Double x;
    @ApiModelProperty(value = "Y坐标", name = "y", required = true)
    private Double y;
    public String toString(){
        return str;
    }
}
