package com.tms.model;

import com.tms.util.IDGen;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class CustomerOrderDetail extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private CustomerOrder customerOrder;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "orderDetail",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Cargo> cargoes;
    private OrderDetailState state;
    private BigDecimal originalPrice;//应付金额
    private BigDecimal payPrice;//实付金额
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Location from;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Location to;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customerOrderDetail",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<DeliverOrder> deliverOrders;
    private CustomerOrder.DeliverType deliverType;
    private String orderDetailNo;
    public CustomerOrderDetail() {
    }

    public CustomerOrderDetail(CustomerOrderDetail customerOrderDetail) {
        this.from=customerOrderDetail.getFrom();
        this.to = customerOrderDetail.getTo();
        this.orderDetailNo = genOrderNo();
        List<Cargo> cargoes = customerOrderDetail.getCargoes();
        cargoes.forEach(cargo -> cargo.setOrderDetail(this));
        this.cargoes = cargoes;
        this.originalPrice = countPrice(customerOrderDetail);
        this.payPrice = this.originalPrice;
        this.deliverType = from.getCityCode().equals(to.getCityCode())?CustomerOrder.DeliverType.SAME_CITY:CustomerOrder.DeliverType.NATIONAL;
        preInsert();
    }
    public enum OrderDetailState {
         UNALLOCATED, TRANSPORTING, COMPLETE, INVALID
    }
    //TODO 计算运费
    private BigDecimal countPrice(CustomerOrderDetail customerOrderDetail) {
        return new BigDecimal("1000");
    }

    public OrderDetailState getState() {
        return state;
    }

    public void setState(OrderDetailState state) {
        this.state = state;
    }

    public String getOrderDetailNo() {
        return orderDetailNo;
    }

    public void setOrderDetailNo(String orderDetailNo) {
        this.orderDetailNo = orderDetailNo;
    }

    public CustomerOrder.DeliverType getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(CustomerOrder.DeliverType deliverType) {
        this.deliverType = deliverType;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public List<DeliverOrder> getDeliverOrders() {
        return deliverOrders;
    }

    public void setDeliverOrders(List<DeliverOrder> deliverOrders) {
        this.deliverOrders = deliverOrders;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<Cargo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
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

    private String genOrderNo() {
        return "D" + IDGen.nextId();
    }
}
