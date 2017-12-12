package com.tms.model;

import com.tms.controller.vo.CustomerOrderVo;
import com.tms.util.IDGen;

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
public class CustomerOrder extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerOrderNo;
    private OrderState state;
    private Payment.PayState payState;
    private String customerRemark;//用户备注
    private String innerRemark;//内部备注
    private Date finishTime;//完成时间
    private OrderSource source;//订单来源
    private Date expireTime;//过期时间
    private BigDecimal originalPrice;//订单应付金额
    private BigDecimal payPrice;//订单实付金额
    private Payment.PayType payType;
    @OneToOne
    @JoinColumn
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder")
    private List<Payment> payments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder")
    private List<CustomerOrderDetail> orderDetails;

    public CustomerOrder(CustomerOrderVo customerOrderVo) {
        Calendar calendar = Calendar.getInstance();
        this.customerOrderNo = genOrderNo();
        switch (customerOrderVo.getPayType()) {
            case SENDER_PAY:
                this.state = OrderState.TEMP;
                calendar.add(Calendar.MINUTE, 30);
                this.expireTime = calendar.getTime();
                break;
            case RECEIVER_PAY:
                this.state = OrderState.UNALLOCATED;
                break;
            case SENDER_ORDER_PAY:
                this.state = OrderState.UNALLOCATED;
                break;
        }
        this.payState = Payment.PayState.UNPAY;
        this.customerRemark = customerOrderVo.getCustomerRemark();
        this.source = customerOrderVo.getSource();
        this.payType = customerOrderVo.getPayType();
        this.originalPrice = countPrice(customerOrderVo.getOrderDetails());
        this.payPrice = this.originalPrice;
        this.customer = customerOrderVo.getCustomer();
        preInsert();
    }

    private BigDecimal countPrice(List<CustomerOrderDetail> orderDetails) {
        List<CustomerOrderDetail> domains = new ArrayList<>();
        BigDecimal amount = new BigDecimal("0");
        for (CustomerOrderDetail customerOrderDetail : orderDetails) {
            CustomerOrderDetail domain = new CustomerOrderDetail(customerOrderDetail);
            domain.setCustomerOrder(this);
            amount.add(domain.getOriginalPrice());
            domains.add(domain);
        }
        this.orderDetails = domains;
        return amount;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public enum OrderState {
        TEMP, UNALLOCATED, TRANSPORTING, COMPLETE, INVALID
    }

    public enum OrderSource {
        PC, WAP, BACK, IOS, ANDROID
    }

    public enum DeliverType {
        SAME_CITY, NATIONAL
    }

    public Payment.PayType getPayType() {
        return payType;
    }

    public void setPayType(Payment.PayType payType) {
        this.payType = payType;
    }

    public CustomerOrder() {
    }

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

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
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

    public OrderSource getSource() {
        return source;
    }

    public void setSource(OrderSource source) {
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

    private String genOrderNo() {
        return "C" + IDGen.nextId();
    }

}
