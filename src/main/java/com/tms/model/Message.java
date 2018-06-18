package com.tms.model;


import com.tms.common.Constant;

import javax.persistence.*;

@Entity
public class Message extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Constant.MsgType msgType;
    @ManyToOne
    @JoinColumn
    private Customer customer;
    private Constant.MsgAuth msgAuth;
    private Boolean isRead;
//    @ManyToOne
//    @JoinColumn
//    private SysUser sysUser;
    @ManyToOne
    @JoinColumn
    private Driver driver;
    @ManyToOne
    @JoinColumn
    private Payment payment;
    @ManyToOne
    @JoinColumn
    private CustomerOrder customerOrder;
    @ManyToOne
    @JoinColumn
    private DeliverOrder deliverOrder;

    private String content;

    public Message() {
        preInsert();
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

//
//    public SysUser getSysUser() {
//        return sysUser;
//    }
//
//    public void setSysUser(SysUser sysUser) {
//        this.sysUser = sysUser;
//    }


    public Constant.MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(Constant.MsgType msgType) {
        this.msgType = msgType;
    }

    public Constant.MsgAuth getMsgAuth() {
        return msgAuth;
    }

    public void setMsgAuth(Constant.MsgAuth msgAuth) {
        this.msgAuth = msgAuth;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public DeliverOrder getDeliverOrder() {
        return deliverOrder;
    }

    public void setDeliverOrder(DeliverOrder deliverOrder) {
        this.deliverOrder = deliverOrder;
    }
}
