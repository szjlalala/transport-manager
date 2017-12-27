package com.tms.controller.vo.response;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.geo.Point;

import java.io.Serializable;

public class LocationResponseVo implements Serializable {
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
    @ApiModelProperty(value = "地址", name = "address")
    private String address;
    @ApiModelProperty(value = "坐标", name = "geo")
    private Point geo;

    public LocationResponseVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public Long getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Long districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point getGeo() {
        return geo;
    }

    public void setGeo(Point geo) {
        this.geo = geo;
    }
}
