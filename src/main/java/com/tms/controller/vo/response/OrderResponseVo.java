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
public class OrderResponseVo implements Serializable {
    @ApiModelProperty(value = "运单id", name = "id")
    private String id;
    @ApiModelProperty(value = "货物", name = "cargoes")
    private List<CargoResponseVo> cargoes;
    @ApiModelProperty(value = "订单详情状态", name = "state")
    private Constant.OrderState state;
    @ApiModelProperty(value = "金额", name = "payment")
    private Payment payment;
    @ApiModelProperty(value = "发货人", name = "from")
    private LocationResponseVo from;
    @ApiModelProperty(value = "收货人", name = "to")
    private LocationResponseVo to;
    @ApiModelProperty(value = "运单", name = "deliverOrders")
    private List<DeliverOrderResponseVo> deliverOrders;
    @ApiModelProperty(value = "配送方式", name = "deliverType")
    private Constant.DeliverType deliverType;
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
    @ApiModelProperty(value = "用户id", name = "customerId")
    private Long customerId;
    @ApiModelProperty(value = "司机id", name = "driverId")
    private Long driverId;
    @ApiModelProperty(value = "距离", name = "distance")
    private Long distance;

    public OrderResponseVo(CustomerOrder customerOrder) {
        BeanUtils.copyProperties(customerOrder, this);
        this.setId(customerOrder.getCustomerOrderNo());
        this.setFrom(customerOrder.getFrom());
        this.setTo(customerOrder.getTo());
        this.setCargoes(customerOrder.getCargoes());
        this.setPayment(customerOrder);
        this.setDeliverOrders(customerOrder.getDeliverOrders());
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
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (from != null)
            BeanUtils.copyProperties(from, locationResponseVo);
        this.from = locationResponseVo;
    }

    public void setTo(Location to) {
        LocationResponseVo locationResponseVo = new LocationResponseVo();
        if (to != null)
            BeanUtils.copyProperties(to, locationResponseVo);
        this.to = locationResponseVo;
    }

    public void setDeliverOrders(List<DeliverOrder> deliverOrders) {
        List<DeliverOrderResponseVo> temp = new ArrayList<>();
        deliverOrders.forEach(deliverOrder -> {
            DeliverOrderResponseVo deliverOrderResponseVo = new DeliverOrderResponseVo(deliverOrder);
            temp.add(deliverOrderResponseVo);
        });
        this.deliverOrders = temp;
    }

    public void setPayment(CustomerOrder customerOrder) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(customerOrder, payment);
        payment.setItems(customerOrder.getPayment().getPaymentItems());
        this.payment = payment;
    }

    @NoArgsConstructor
    @Data
    class Payment {
        @ApiModelProperty(value = "实付金额", name = "payPrice")
        private BigDecimal deliverPrice;
        private BigDecimal insurancePrice;
        private BigDecimal payPrice;
        private BigDecimal originalPrice;
        private Constant.PayType payType;
        private Constant.PayState payState;
        private List<PaymentItemResponseVo> items;

        public Payment(BigDecimal payPrice) {
            this.payPrice = payPrice;
        }

        public void setItems(List<PaymentItem> paymentItems) {
            List<PaymentItemResponseVo> temp = new ArrayList<>();
            paymentItems.forEach(paymentItem -> {
                PaymentItemResponseVo paymentItemResponseVo = new PaymentItemResponseVo();
                BeanUtils.copyProperties(paymentItem, paymentItemResponseVo);
                temp.add(paymentItemResponseVo);
            });
            this.items = temp;
        }
    }


    @NoArgsConstructor
    @Data
    class PaymentItemResponseVo implements Serializable {
        @ApiModelProperty(value = "id", name = "id")
        private String id;
        @ApiModelProperty(value = "支付状态", name = "payState")
        private Constant.PayState payState;
        @ApiModelProperty(value = "应支付价格", name = "payPrice")
        private BigDecimal payPrice;
        @ApiModelProperty(value = "支付方式", name = "payChannel")
        private Constant.PayChannel payChannel;
        @ApiModelProperty(value = "交易完成时间", name = "finishTime")
        private Date finishTime;
        @ApiModelProperty(value = "交易流水号", name = "tradeNo")
        private String tradeNo;

    }

}
