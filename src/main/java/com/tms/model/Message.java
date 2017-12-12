package com.tms.model;

import com.tms.security.SysUser;

import javax.persistence.*;

@Entity
public class Message extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private MsgType msgType;
    @ManyToOne
    @JoinColumn
    private Customer customer;
    private MsgAuth msgAuth;
    private Boolean isRead;
    @ManyToOne
    @JoinColumn
    private SysUser sysUser;
    @ManyToOne
    @JoinColumn
    private Driver driver;
    @ManyToOne
    @JoinColumn
    private CustomerOrder customerOrder;
    @ManyToOne
    @JoinColumn
    private CustomerOrderDetail customerOrderDetail;
    @ManyToOne
    @JoinColumn
    private DeliverOrder deliverOrder;

    private String content;

    public Message() {
        preInsert();
    }

    public enum MsgType {
        NEW_ORDER
    }

    public enum MsgAuth {
        CUSTOMER, MANAGER, DRIVER
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public MsgAuth getMsgAuth() {
        return msgAuth;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public void setMsgAuth(MsgAuth msgAuth) {
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

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
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

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public CustomerOrderDetail getCustomerOrderDetail() {
        return customerOrderDetail;
    }

    public void setCustomerOrderDetail(CustomerOrderDetail customerOrderDetail) {
        this.customerOrderDetail = customerOrderDetail;
    }

    public DeliverOrder getDeliverOrder() {
        return deliverOrder;
    }

    public void setDeliverOrder(DeliverOrder deliverOrder) {
        this.deliverOrder = deliverOrder;
    }
}
