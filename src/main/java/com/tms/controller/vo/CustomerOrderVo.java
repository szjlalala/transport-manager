package com.tms.controller.vo;

import com.tms.model.Customer;
import com.tms.model.CustomerOrder;
import com.tms.model.CustomerOrderDetail;
import com.tms.model.Payment;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerOrderVo implements Serializable {
    private Long id;
    private String customerOrderNo;
    private CustomerOrder.OrderState state;
    private Payment.PayState payState;
    private String customerRemark;//用户备注
    private String innerRemark;//内部备注
    private Date finishTime;//完成时间
    private CustomerOrder.OrderSource source;//订单来源
    private Date expireTime;//过期时间
    private BigDecimal originalPrice;//订单应付金额
    private BigDecimal payPrice;//订单实付金额
    @NotEmpty(message = "支付方式不能为空")
    private Payment.PayType payType;
    private Customer customer;
    private List<CustomerOrderDetail> orderDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerOrderNo() {
        return customerOrderNo;
    }

    public void setCustomerOrderNo(String customerOrderNo) {
        this.customerOrderNo = customerOrderNo;
    }

    public CustomerOrder.OrderState getState() {
        return state;
    }

    public void setState(CustomerOrder.OrderState state) {
        this.state = state;
    }

    public Payment.PayState getPayState() {
        return payState;
    }

    public void setPayState(Payment.PayState payState) {
        this.payState = payState;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getInnerRemark() {
        return innerRemark;
    }

    public void setInnerRemark(String innerRemark) {
        this.innerRemark = innerRemark;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public CustomerOrder.OrderSource getSource() {
        return source;
    }

    public void setSource(CustomerOrder.OrderSource source) {
        this.source = source;
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

    public Payment.PayType getPayType() {
        return payType;
    }

    public void setPayType(Payment.PayType payType) {
        this.payType = payType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CustomerOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<CustomerOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
