package com.tms.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CreateOrderLocationRequestVo implements Serializable {
    @ApiModelProperty(value = "姓名", name = "name",required=true)
    private String name;
    @ApiModelProperty(value = "电话", name = "phone",required=true)
    private String phone;
    @ApiModelProperty(value = "省编码", name = "provinceCode",required=true)
    private Long provinceCode;
    @ApiModelProperty(value = "市编码", name = "cityCode",required=true)
    private Long cityCode;
    @ApiModelProperty(value = "区编码", name = "districtCode",required=true)
    private Long districtCode;
    @ApiModelProperty(value = "详细地址", name = "address",required=true)
    private String address;
    @ApiModelProperty(value = "X坐标", name = "x",required=true)
    private String x;
    @ApiModelProperty(value = "Y坐标", name = "y",required=true)
    private String y;

    public CreateOrderLocationRequestVo() {
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

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
