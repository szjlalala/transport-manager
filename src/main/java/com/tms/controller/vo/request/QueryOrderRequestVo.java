package com.tms.controller.vo.request;

import com.tms.model.CustomerOrder;
import com.tms.model.Payment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "查询订单详情请求参数")
public class QueryOrderRequestVo implements Serializable {
    @ApiModelProperty(value = "订单状态", name = "state")
    private CustomerOrder.OrderState state;
    @ApiModelProperty(value = "订单号", name = "customerOrderNo")
    private String customerOrderNo;
    @ApiModelProperty(value = "用户Id", name = "customerId")
    private Long customerId;
    @ApiModelProperty(value = "开始时间", name = "startTime")
    private Date startTime;
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;
    @ApiModelProperty(value = "支付状态", name = "payState")
    private Payment.PayState payState;
    @ApiModelProperty(value = "支付方式", name = "payType")
    private Payment.PayType payType;
    @ApiModelProperty(value = "发货人", name = "from")
    private QueryLocationQuestVo from;
    @ApiModelProperty(value = "收货人", name = "to")
    private QueryLocationQuestVo to;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public QueryLocationQuestVo getFrom() {
        return from;
    }

    public void setFrom(QueryLocationQuestVo from) {
        this.from = from;
    }

    public QueryLocationQuestVo getTo() {
        return to;
    }

    public void setTo(QueryLocationQuestVo to) {
        this.to = to;
    }

    public QueryOrderRequestVo() {
    }

}
