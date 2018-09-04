package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.PostOrderDto;
import com.tms.util.IDGen;
import lombok.Data;

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
@Data
public class Payment extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String no;
    private Constant.PayState payState;
    private Date finishTime;//完成时间
    private Date expireTime;//过期时间
    private BigDecimal originalPrice;//订单应付金额
    private BigDecimal payPrice;//订单实付金额
    private BigDecimal deliverPrice;//运费
    private BigDecimal insurancePrice;//保价金额
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

    private static String genNo() {
        return "P" + IDGen.nextId();
    }


    public Payment(PostOrderDto postOrderDto) {
        this.payState = Constant.PayState.UNPAY;
        this.originalPrice = postOrderDto.getPayment().getPayPrice();
        this.payPrice = postOrderDto.getPayment().getPayPrice();
        this.deliverPrice = postOrderDto.getPayment().getDeliverPrice();
        this.insurancePrice = postOrderDto.getPayment().getInsurancePrice();
        this.customerOrders = CustomerOrder.buildOrders(postOrderDto,this);
        if (postOrderDto.getPayment().getPayType().equals(Constant.PayType.SENDER_PAY)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 30);
            this.expireTime = calendar.getTime();
        }
//        this.customer = new Customer(postOrderDto.getCustomerId());
        this.payType = postOrderDto.getPayment().getPayType();
        this.setNo(genNo());
        preInsert();
    }
}
