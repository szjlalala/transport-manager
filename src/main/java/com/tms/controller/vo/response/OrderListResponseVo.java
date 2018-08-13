package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.controller.vo.request.Address;
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
        BeanUtils.copyProperties(customerOrder, this, new String[]{"from", "to", "payment"});
        this.setFrom(customerOrder.getFrom());
        this.setTo(customerOrder.getTo());
        this.setId(customerOrder.getCustomerOrderNo());
        this.setPayment(new Payment(customerOrder.getPayment().getPayPrice()));
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setFrom(Location from) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (from != null)
            BeanUtils.copyProperties(from, locationResponseVo);
        Address address = new Address();
        address.setX(from.getGeo().getX());
        address.setY(from.getGeo().getY());
        address.setStr(from.getDetail());
        locationResponseVo.setAddress(address);
//        this.from.setDistrict(from.getDistrict());
        this.from = locationResponseVo;
    }

    public void setTo(Location to) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (to != null)
            BeanUtils.copyProperties(to, locationResponseVo);
        Address address = new Address();
        address.setX(to.getGeo().getX());
        address.setY(to.getGeo().getY());
        address.setStr(to.getDetail());
        locationResponseVo.setAddress(address);
        this.to= locationResponseVo;
    }

    @NoArgsConstructor
    @Data
     class Payment {
        @ApiModelProperty(value = "订单列表显示金额", name = "payPrice")
        private BigDecimal payPrice;
        public Payment(BigDecimal payPrice) {
            this.payPrice = payPrice;
        }
    }
}
