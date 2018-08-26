package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.controller.vo.BaseVo;
import com.tms.model.Customer;
import com.tms.model.CustomerOrder;
import com.tms.model.Payment;
import com.tms.model.PaymentItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "查询订单详情返回结果")
public class PaymentResponseVo extends BaseVo implements Serializable {
    @ApiModelProperty(value = "订单状态", name = "state")
    private Constant.OrderState state;
    @ApiModelProperty(value = "订单号", name = "customerOrderNo")
    private String customerOrderNo;
    @ApiModelProperty(value = "支付状态", name = "payState")
    private Constant.PayState payState;
    @ApiModelProperty(value = "支付方式", name = "payType")
    private Constant.PayType payType;
    @ApiModelProperty(value = "用户备注", name = "customerRemark")
    private String customerRemark;
    @ApiModelProperty(value = "内部备注", name = "innerRemark")
    private String innerRemark;
    @ApiModelProperty(value = "完成时间", name = "finishTime")
    private Date finishTime;
    @ApiModelProperty(value = "订单来源", name = "source")
    private Constant.OrderSource source;//订单来源
    @ApiModelProperty(value = "过期时间", name = "expireTime")
    private Date expireTime;
    @ApiModelProperty(value = "订单应付金额", name = "originalPrice")
    private BigDecimal originalPrice;//
    @ApiModelProperty(value = "订单实付金额", name = "payPrice")
    private BigDecimal payPrice;//
    @ApiModelProperty(value = "用户", name = "customer")
    private CustomerResponseVo customer;
    @ApiModelProperty(value = "支付信息", name = "payments")
    private List<PaymentItemResponseVo> payments;
    @ApiModelProperty(value = "订单详情", name = "orderDetails")
    private List<OrderResponseVo> orderDetails;

    public PaymentResponseVo(Payment payment) {
        BeanUtils.copyProperties(payment,this);
    }


    public void setCustomer(Customer customer) {
        CustomerResponseVo customerResponseVo = new CustomerResponseVo();
        if (customer != null)
            BeanUtils.copyProperties(customer, customerResponseVo);
        this.customer = customerResponseVo;
    }

    public void setPayments(List<PaymentItem> paymentItems) {
        List<PaymentItemResponseVo> temp = new ArrayList<>();
        paymentItems.forEach(payment -> {
            PaymentItemResponseVo paymentItemResponseVo = new PaymentItemResponseVo();
            BeanUtils.copyProperties(payment, paymentItemResponseVo);
            temp.add(paymentItemResponseVo);
        });
        this.payments = temp;
    }

    public void setOrderDetails(List<CustomerOrder> orderDetails) {
        List<OrderResponseVo> temp = new ArrayList<>();
        orderDetails.forEach(customerOrder -> {
            OrderResponseVo responseVo = new OrderResponseVo();
            BeanUtils.copyProperties(customerOrder, responseVo);
            temp.add(responseVo);
        });
        this.orderDetails = temp;
    }
}
