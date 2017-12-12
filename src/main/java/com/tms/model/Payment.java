package com.tms.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class Payment extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PayState payState;
    private BigDecimal payPrice;//应支付价格
    @Enumerated(EnumType.STRING)
    private PayChannel payChannel;//对接支付平台支付方式
    private String tradeNo;//支付平台异步返回交易流水编号
    private Date finishTime;//交易完成时间
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;//订单号

    public enum PayState {
        UNPAY, COMPLETE, PARTPAY;

    }
    public enum PayType {
        SENDER_PAY, RECEIVER_PAY, SENDER_ORDER_PAY;

    }

    enum PayChannel {
        ALIPAY("ALIPAY", "支付宝"),
        WECHATPAY("WECHATPAY", "微信(APP)"),
        POS("POS", "POS"),
        CASH("CASH", "现金");

        private String channel;
        private String desc;

        private PayChannel(String channel, String desc) {
            this.channel = channel;
            this.desc = desc;
        }

        public String getChannel() {
            return this.channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PayState getPayState() {
        return payState;
    }

    public void setPayState(PayState payState) {
        this.payState = payState;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public PayChannel getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(PayChannel payChannel) {
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

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
