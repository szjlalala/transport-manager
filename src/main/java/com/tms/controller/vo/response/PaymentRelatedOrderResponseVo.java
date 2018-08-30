package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class PaymentRelatedOrderResponseVo implements Serializable {
    @ApiModelProperty(value = "运单id", name = "id")
    private String id;
    @ApiModelProperty(value = "货物", name = "cargoes")
    private List<CargoResponseVo> cargoes;
    @ApiModelProperty(value = "订单详情状态", name = "state")
    private Constant.OrderState state;
    @ApiModelProperty(value = "金额", name = "payPrice")
    private BigDecimal payPrice;
    @ApiModelProperty(value = "发货人", name = "from")
    private LocationResponseVo from;
    @ApiModelProperty(value = "收货人", name = "to")
    private LocationResponseVo to;
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
    @ApiModelProperty(value = "用户id", name = "customerId")
    private Long customerId;
    @ApiModelProperty(value = "距离", name = "distance")
    private Long distance;

    public PaymentRelatedOrderResponseVo(CustomerOrder customerOrder) {
        BeanUtils.copyProperties(customerOrder, this);
        this.setId(customerOrder.getCustomerOrderNo());
        this.setFrom(customerOrder.getFrom());
        this.setTo(customerOrder.getTo());
        this.setCargoes(customerOrder.getCargoes());
        this.setPayPrice(customerOrder);
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


    public void setFrom(Location from) {
        this.from = LocationResponseVo.genLocationResponseVoFromLocation(from);
    }

    public void setTo(Location to) {
        this.to = LocationResponseVo.genLocationResponseVoFromLocation(to);
    }

    public void setPayPrice(CustomerOrder customerOrder) {
        this.payPrice = customerOrder.getSubPayment().getPayPrice();
    }

}
