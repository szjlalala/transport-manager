package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.model.CustomerOrder;
import com.tms.model.Location;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
@ApiModel(value = "用户订单列表返回值")
public class OrderListResponseVo implements Serializable {

    @ApiModelProperty(value = "订单状态", name = "state")
    private Constant.OrderState state;
    @ApiModelProperty(value = "金额", name = "payment")
    private Payment payment;
    @ApiModelProperty(value = "发货人", name = "from")
    private LocationResponseVo from;
    @ApiModelProperty(value = "收货人", name = "to")
    private LocationResponseVo to;
    @ApiModelProperty(value = "运单id", name = "id")
    private String id;
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
    @ApiModelProperty(value = "用户id", name = "customerId")
    private Long customerId;

    public OrderListResponseVo(CustomerOrder customerOrder) {
        BeanUtils.copyProperties(customerOrder, this);
        this.setFrom(customerOrder.getFrom());
        this.setTo(customerOrder.getTo());
        this.setId(customerOrder.getCustomerOrderNo());
        this.setPayment(customerOrder.getPayPrice());
    }

    public void setPayment(BigDecimal payPrice) {
        this.payment = new Payment(payPrice);
    }

    public void setFrom(Location from) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (from != null)
            BeanUtils.copyProperties(from, locationResponseVo);
        locationResponseVo.setX(from.getGeo().getX());
        locationResponseVo.setY(from.getGeo().getY());
        this.from = locationResponseVo;
    }

    public void setTo(Location to) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (to != null)
            BeanUtils.copyProperties(to, locationResponseVo);
        locationResponseVo.setX(to.getGeo().getX());
        locationResponseVo.setY(to.getGeo().getY());
        this.to= locationResponseVo;
    }

    @NoArgsConstructor
    @Data
     class Payment {
        @ApiModelProperty(value = "实付金额", name = "payPrice")
        private BigDecimal payPrice;

        public Payment(BigDecimal payPrice) {
            this.payPrice = payPrice;
        }
    }
}
