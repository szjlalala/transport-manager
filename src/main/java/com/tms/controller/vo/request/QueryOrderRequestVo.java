package com.tms.controller.vo.request;

import com.tms.model.CustomerOrder;
import com.tms.model.Payment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "创建订单详情请求参数")
public class QueryOrderRequestVo implements Serializable {
    @ApiModelProperty(value = "用户备注", name = "customerRemark", example = "轻拿轻放")
    private String customerRemark;
    @ApiModelProperty(value = "订单来源", name = "source",required=true)
    private CustomerOrder.OrderSource source;
    @ApiModelProperty(value = "支付方式", name = "payType",required=true)
    private Payment.PayType payType;
    @ApiModelProperty(value = "订单详情", name = "orderDetails",required=true)
    private List<CreateOrderDetailRequestVo> orderDetails;
    @ApiModelProperty(value = "下单用户", name = "customerId")
    private Long customerId;

    public QueryOrderRequestVo() {
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public CustomerOrder.OrderSource getSource() {
        return source;
    }

    public void setSource(CustomerOrder.OrderSource source) {
        this.source = source;
    }

    public Payment.PayType getPayType() {
        return payType;
    }

    public void setPayType(Payment.PayType payType) {
        this.payType = payType;
    }

    public List<CreateOrderDetailRequestVo> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<CreateOrderDetailRequestVo> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
