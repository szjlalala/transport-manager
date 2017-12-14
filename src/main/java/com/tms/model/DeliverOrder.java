package com.tms.model;


import com.tms.util.IDGen;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DeliverOrder extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deliverOrderNo;
    @OneToOne
    @JoinColumn
    private Location from;
    @OneToOne
    @JoinColumn
    private Location to;
    private Date startTime;
    private Date endTime;
    @OneToOne
    @JoinColumn
    private Driver driver;
    @OneToOne
    @JoinColumn
    private Voyage voyage;
    @ManyToOne
    @JoinColumn(name = "customer_order_detail_id")
    private CustomerOrderDetail customerOrderDetail;

    private DeliverOrderState deliverOrderState;

    private Integer sequence;

    public DeliverOrder(CustomerOrderDetail customerOrderDetail, Integer sequence) {
        this.deliverOrderNo = genOrderNo();
        this.sequence = sequence;
        this.from=customerOrderDetail.getFrom();
        this.to = customerOrderDetail.getTo();
        this.customerOrderDetail = customerOrderDetail;
        this.deliverOrderState = DeliverOrderState.UNALLOCATED;
    }

    public enum DeliverOrderState {
        UNALLOCATED, TRANSPORTING, COMPLETE,CANCEL
    }

    public String getDeliverOrderNo() {
        return deliverOrderNo;
    }

    public void setDeliverOrderNo(String deliverOrderNo) {
        this.deliverOrderNo = deliverOrderNo;
    }

    public CustomerOrderDetail getCustomerOrderDetail() {
        return customerOrderDetail;
    }

    public void setCustomerOrderDetail(CustomerOrderDetail customerOrderDetail) {
        this.customerOrderDetail = customerOrderDetail;
    }

    public DeliverOrder() {
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public DeliverOrderState getDeliverOrderState() {
        return deliverOrderState;
    }

    public void setDeliverOrderState(DeliverOrderState deliverOrderState) {
        this.deliverOrderState = deliverOrderState;
    }

    private String genOrderNo() {
        return "D" + IDGen.nextId();
    }
}
