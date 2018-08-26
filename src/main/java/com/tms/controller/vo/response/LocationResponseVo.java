package com.tms.controller.vo.response;

import com.tms.controller.vo.request.Address;
import com.tms.model.Location;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    private long district;
    @ApiModelProperty(value = "地址", name = "address", required = true)
    private Address address;

    public static LocationResponseVo genLocationResponseVoFromLocation(Location location){
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (location != null)
            BeanUtils.copyProperties(location, locationResponseVo);
        Address address = new Address();
        address.setX(location.getGeo().getX());
        address.setY(location.getGeo().getY());
        address.setStr(location.getAddress());
        locationResponseVo.setAddress(address);
        return locationResponseVo;
    }
}
