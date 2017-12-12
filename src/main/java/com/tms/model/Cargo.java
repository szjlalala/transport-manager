package com.tms.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class Cargo extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;//货物名称
    private Long count;//数量
    private BigDecimal weight;//重量单位kg
    private BigDecimal volume;//体积单位立方
    private BigDecimal price;//价值
    private CargoType cargoType;//货物类型
    private String remark;//备注
    @ManyToOne
    @JoinColumn
    private CustomerOrderDetail orderDetail;

    public CustomerOrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(CustomerOrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Cargo() {
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public enum CargoType {
        NORMAL, FRAGILE
    }
}
