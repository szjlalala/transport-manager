package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.PostOrderDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class Payment extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Constant.PayState payState;
    private Date finishTime;//完成时间
    private Date expireTime;//过期时间
    private BigDecimal originalPrice;//订单应付金额
    private BigDecimal payPrice;//订单实付金额
    private Constant.PayType payType;
    @OneToOne
    @JoinColumn
    private Customer customer;
    @OneToMany(mappedBy = "payment")
    private List<PaymentItem> paymentItems;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payment")
    private List<CustomerOrder> customerOrders;

    public Payment() {
    }

    public Payment(PostOrderDto postOrderDto) {
        this.payState = Constant.PayState.UNPAY;
        this.originalPrice = postOrderDto.getPayment().getPayPrice();
        this.payPrice = postOrderDto.getPayment().getPayPrice();
        this.customerOrders = CustomerOrder.buildOrders(postOrderDto,this);
        if (postOrderDto.getPayment().getPayType().equals(Constant.PayType.SENDER_PAY)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 30);
            this.expireTime = calendar.getTime();
        }
//        this.customer = new Customer(postOrderDto.getCustomerId());
        this.payType = postOrderDto.getPayment().getPayType();
        preInsert();
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

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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

    public Constant.PayType getPayType() {
        return payType;
    }

    public void setPayType(Constant.PayType payType) {
        this.payType = payType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<PaymentItem> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<PaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }
}
