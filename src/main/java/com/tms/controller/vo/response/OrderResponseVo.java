package com.tms.controller.vo.response;

import com.tms.controller.vo.BaseVo;
import com.tms.model.Customer;
import com.tms.model.CustomerOrder;
import com.tms.model.CustomerOrderDetail;
import com.tms.model.Payment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "查询订单详情返回结果")
public class OrderResponseVo extends BaseVo implements Serializable {
    @ApiModelProperty(value = "订单状态", name = "state")
    private CustomerOrder.OrderState state;
    @ApiModelProperty(value = "订单号", name = "customerOrderNo")
    private String customerOrderNo;
    @ApiModelProperty(value = "支付状态", name = "payState")
    private Payment.PayState payState;
    @ApiModelProperty(value = "支付方式", name = "payType")
    private Payment.PayType payType;
    @ApiModelProperty(value = "用户备注", name = "customerRemark")
    private String customerRemark;
    @ApiModelProperty(value = "内部备注", name = "innerRemark")
    private String innerRemark;
    @ApiModelProperty(value = "完成时间", name = "finishTime")
    private Date finishTime;
    @ApiModelProperty(value = "订单来源", name = "source")
    private CustomerOrder.OrderSource source;//订单来源
    @ApiModelProperty(value = "过期时间", name = "expireTime")
    private Date expireTime;
    @ApiModelProperty(value = "订单应付金额", name = "originalPrice")
    private BigDecimal originalPrice;//
    @ApiModelProperty(value = "订单实付金额", name = "payPrice")
    private BigDecimal payPrice;//
    @ApiModelProperty(value = "用户", name = "customer")
    private CustomerResponseVo customer;
    @ApiModelProperty(value = "支付信息", name = "payments")
    private List<PaymentResponseVo> payments;
    @ApiModelProperty(value = "订单详情", name = "orderDetails")
    private List<OrderDetailResponseVo> orderDetails;

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

    public CustomerResponseVo getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        CustomerResponseVo customerResponseVo = new CustomerResponseVo();
        if (customer != null)
            BeanUtils.copyProperties(customer, customerResponseVo);
        this.customer = customerResponseVo;
    }

    public List<PaymentResponseVo> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        List<PaymentResponseVo> temp = new ArrayList<>();
        payments.forEach(payment -> {
            PaymentResponseVo paymentResponseVo = new PaymentResponseVo();
            BeanUtils.copyProperties(payment, paymentResponseVo);
            temp.add(paymentResponseVo);
        });
        this.payments = temp;
    }

    public List<OrderDetailResponseVo> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<CustomerOrderDetail> orderDetails) {
        List<OrderDetailResponseVo> temp = new ArrayList<>();
        orderDetails.forEach(orderDetail -> {
            OrderDetailResponseVo responseVo = new OrderDetailResponseVo();
            BeanUtils.copyProperties(orderDetail, responseVo);
            temp.add(responseVo);
        });
        this.orderDetails = temp;
    }

    public CustomerOrder.OrderState getState() {
        return state;
    }

    public void setState(CustomerOrder.OrderState state) {
        this.state = state;
    }

    public String getCustomerOrderNo() {
        return customerOrderNo;
    }

    public void setCustomerOrderNo(String customerOrderNo) {
        this.customerOrderNo = customerOrderNo;
    }


    public Payment.PayState getPayState() {
        return payState;
    }

    public void setPayState(Payment.PayState payState) {
        this.payState = payState;
    }

    public Payment.PayType getPayType() {
        return payType;
    }

    public void setPayType(Payment.PayType payType) {
        this.payType = payType;
    }


    public OrderResponseVo() {
    }

}
