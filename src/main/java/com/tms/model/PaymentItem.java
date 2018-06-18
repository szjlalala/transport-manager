package com.tms.model;

import com.tms.common.Constant;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class PaymentItem extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Constant.PayState payState;
    private BigDecimal payPrice;//应支付价格
    @Enumerated(EnumType.STRING)
    private Constant.PayChannel payChannel;//对接支付平台支付方式
    private String tradeNo;//支付平台异步返回交易流水编号
    private Date finishTime;//交易完成时间
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private Payment payment;//订单号

    public PaymentItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Constant.PayState getPayState() {
        return payState;
    }

    public void setPayState(Constant.PayState payState) {
        this.payState = payState;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Constant.PayChannel getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Constant.PayChannel payChannel) {
        this.payChannel = payChannel;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
