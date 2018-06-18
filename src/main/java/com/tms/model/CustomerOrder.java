package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.PostOrderDto;
import com.tms.controller.vo.response.LocationResponseVo;
import com.tms.util.IDGen;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class CustomerOrder extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Payment payment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Cargo> cargoes;
    private BigDecimal deliverPrice;//运费
    private BigDecimal insurancePrice;//保价金额
    private BigDecimal originalPrice;//应付金额
    private BigDecimal payPrice;//实付金额
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Location from;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Location to;
    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<DeliverOrder> deliverOrders;
    private Constant.DeliverType deliverType;
    private String customerOrderNo;
    private Long distance;
    private Constant.OrderState state;
    private Constant.OrderSource source;//订单来源
    private String customerRemark;//用户备注
    private String innerRemark;//内部备注

    public CustomerOrder() {
    }

    public static List<CustomerOrder> buildOrders(PostOrderDto postOrderDto, Payment payment) {
        List<CustomerOrder> domains = new ArrayList<>();
        for (PostOrderDto.OrderDto orderDto : postOrderDto.getOrders()) {
            CustomerOrder domain = new CustomerOrder();
            if (postOrderDto.getPayment().getPayType().equals(Constant.PayType.SENDER_PAY)) {
                domain.state = Constant.OrderState.TEMP;
            } else {
                domain.state = Constant.OrderState.UNALLOCATED;
            }
            domain.from = new Location(postOrderDto.getFrom());
            domain.to = new Location(orderDto.getTo());
            domain.customerOrderNo = genOrderNo();
            domain.cargoes = Cargo.formatCargoes(orderDto.getCargoes(), domain);
            domain.originalPrice = orderDto.getPayment().getPayPrice();
            domain.payPrice = orderDto.getPayment().getPayPrice();
            domain.insurancePrice = orderDto.getPayment().getInsurancePrice();
            domain.deliverPrice = orderDto.getPayment().getDeliverPrice();
            domain.payPrice = orderDto.getPayment().getPayPrice();
            domain.deliverType = domain.from.getCity().equals(domain.to.getCity()) ? Constant.DeliverType.SAME_CITY : Constant.DeliverType.NATIONAL;
            domain.distance = orderDto.getDistance();
            domain.preInsert();
            domain.setPayment(payment);
            domains.add(domain);
        }
        return domains;
    }

    //TODO 计算运费
//    private BigDecimal countPrice(CreateOrderRequestVo customerOrderDetail) {
//        return new BigDecimal("1000");
//    }

    private static String genOrderNo() {
        return "C" + IDGen.nextId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Cargo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }

    public BigDecimal getDeliverPrice() {
        return deliverPrice;
    }

    public void setDeliverPrice(BigDecimal deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public BigDecimal getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(BigDecimal insurancePrice) {
        this.insurancePrice = insurancePrice;
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

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public void setFrom(Location from) {
        this.from = from;
    }


    public void setTo(Location to) {
        this.to = to;
    }

    public List<DeliverOrder> getDeliverOrders() {
        return deliverOrders;
    }

    public void setDeliverOrders(List<DeliverOrder> deliverOrders) {
        this.deliverOrders = deliverOrders;
    }

    public Constant.DeliverType getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(Constant.DeliverType deliverType) {
        this.deliverType = deliverType;
    }

    public String getCustomerOrderNo() {
        return customerOrderNo;
    }

    public void setCustomerOrderNo(String customerOrderNo) {
        this.customerOrderNo = customerOrderNo;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Constant.OrderState getState() {
        return state;
    }

    public void setState(Constant.OrderState state) {
        this.state = state;
    }

    public Constant.OrderSource getSource() {
        return source;
    }

    public void setSource(Constant.OrderSource source) {
        this.source = source;
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
}
