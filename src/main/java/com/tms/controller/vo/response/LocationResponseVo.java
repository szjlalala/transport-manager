package com.tms.controller.vo.response;

import com.tms.controller.vo.request.Address;
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
    @ApiModelProperty(value = "地区", name = "district")
    private String district;
    @ApiModelProperty(value = "地址", name = "address", required = true)
    private Address address;
}
