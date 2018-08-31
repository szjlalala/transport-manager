package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.PostOrderDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class DeliverCargo extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;//货物名称
    private Long count;//数量
    private BigDecimal weight;//重量单位kg
    private BigDecimal volume;//体积单位立方
    private BigDecimal price;//价值
    private Constant.CargoType cargoType;//货物类型
    private String remark;//备注
    @ManyToOne
    @JoinColumn
    private DeliverOrder deliverOrder;

    public DeliverCargo() {
    }

    public DeliverCargo(PostOrderDto.CargoDto cargoDto) {
        BeanUtils.copyProperties(cargoDto, this);
    }

    public Constant.CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(Constant.CargoType cargoType) {
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

    public DeliverOrder getDeliverOrder() {
        return deliverOrder;
    }

    public void setDeliverOrder(DeliverOrder deliverOrder) {
        this.deliverOrder = deliverOrder;
    }

    public static List<DeliverCargo> formatCargoes(List<PostOrderDto.CargoDto> cargoes, DeliverOrder deliverOrder) {

        List<DeliverCargo> domainList = new ArrayList<>();
        cargoes.forEach(cargoDto -> {
            DeliverCargo cargo = new DeliverCargo(cargoDto);
            cargo.setDeliverOrder(deliverOrder);
            domainList.add(cargo);
        });
        return domainList;
    }
}
