package com.tms.controller.vo.response;

import com.tms.model.Cargo;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class CargoResponseVo implements Serializable {
    @ApiModelProperty(value = "货物名称", name = "name")
    private String name;
    @ApiModelProperty(value = "数量", name = "count")
    private Long count;
    @ApiModelProperty(value = "重量单位kg", name = "weight")
    private BigDecimal weight;
    @ApiModelProperty(value = "体积单位立方", name = "volume")
    private BigDecimal volume;
    @ApiModelProperty(value = "价值", name = "price")
    private BigDecimal price;
    @ApiModelProperty(value = "货物类型", name = "cargoType")
    private Cargo.CargoType cargoType;
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Cargo.CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(Cargo.CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public CargoResponseVo() {

    }
}
