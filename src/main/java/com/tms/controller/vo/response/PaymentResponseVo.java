package com.tms.controller.vo.response;

import com.tms.model.Payment;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PaymentResponseVo implements Serializable {
    @ApiModelProperty(value = "支付状态", name = "payState")
    private Payment.PayState payState;
    @ApiModelProperty(value = "应支付价格", name = "payPrice")
    private BigDecimal payPrice;
    @ApiModelProperty(value = "支付方式", name = "payChannel")
    private Payment.PayChannel payChannel;
    @ApiModelProperty(value = "交易完成时间", name = "finishTime")
    private Date finishTime;

    public PaymentResponseVo() {
    }

    public Payment.PayState getPayState() {
        return payState;
    }

    public void setPayState(Payment.PayState payState) {
        this.payState = payState;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Payment.PayChannel getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Payment.PayChannel payChannel) {
        this.payChannel = payChannel;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
