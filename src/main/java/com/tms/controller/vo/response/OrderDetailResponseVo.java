package com.tms.controller.vo.response;

import com.tms.model.*;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailResponseVo implements Serializable {
    @ApiModelProperty(value = "货物", name = "cargoes")
    private List<CargoResponseVo> cargoes;
    @ApiModelProperty(value = "订单详情状态", name = "state")
    private CustomerOrderDetail.OrderDetailState state;
    @ApiModelProperty(value = "应付金额", name = "originalPrice")
    private BigDecimal originalPrice;
    @ApiModelProperty(value = "实付金额", name = "payPrice")
    private BigDecimal payPrice;
    @ApiModelProperty(value = "发货人", name = "from")
    private LocationResponseVo from;
    @ApiModelProperty(value = "收货人", name = "to")
    private LocationResponseVo to;
    @ApiModelProperty(value = "运单", name = "deliverOrders")
    private List<DeliverOrderResponseVo> deliverOrders;
    @ApiModelProperty(value = "配送方式", name = "deliverType")
    private CustomerOrder.DeliverType deliverType;
    @ApiModelProperty(value = "运单id", name = "orderDetailNo")
    private String orderDetailNo;

    public OrderDetailResponseVo() {
    }

    public List<CargoResponseVo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<Cargo> cargoes) {
        List<CargoResponseVo> temp = new ArrayList<>();
        cargoes.forEach(cargo -> {
            CargoResponseVo cargoResponseVo = new CargoResponseVo();
            BeanUtils.copyProperties(cargo, cargoResponseVo);
            temp.add(cargoResponseVo);
        });
        this.cargoes = temp;
    }

    public CustomerOrderDetail.OrderDetailState getState() {
        return state;
    }

    public void setState(CustomerOrderDetail.OrderDetailState state) {
        this.state = state;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public LocationResponseVo getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (from != null)
            BeanUtils.copyProperties(from, locationResponseVo);
        this.from = locationResponseVo;
    }

    public LocationResponseVo getTo() {
        return to;
    }

    public void setTo(Location to) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (to != null)
            BeanUtils.copyProperties(to, locationResponseVo);
        this.to= locationResponseVo;
    }

    public List<DeliverOrderResponseVo> getDeliverOrders() {
        return deliverOrders;
    }

    public void setDeliverOrders(List<DeliverOrder> deliverOrders) {
        List<DeliverOrderResponseVo> temp = new ArrayList<>();
        deliverOrders.forEach(deliverOrder -> {
            DeliverOrderResponseVo deliverOrderResponseVo = new DeliverOrderResponseVo();
            BeanUtils.copyProperties(deliverOrder, deliverOrderResponseVo);
            temp.add(deliverOrderResponseVo);
        });
        this.deliverOrders = temp;
    }

    public CustomerOrder.DeliverType getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(CustomerOrder.DeliverType deliverType) {
        this.deliverType = deliverType;
    }

    public String getOrderDetailNo() {
        return orderDetailNo;
    }

    public void setOrderDetailNo(String orderDetailNo) {
        this.orderDetailNo = orderDetailNo;
    }
}
