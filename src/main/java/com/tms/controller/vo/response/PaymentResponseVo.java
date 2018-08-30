package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.controller.vo.BaseVo;
import com.tms.model.Customer;
import com.tms.model.CustomerOrder;
import com.tms.model.Payment;
import com.tms.model.PaymentItem;
import io.swagger.annotations.ApiModel;
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
    private CustomerResponseVo customer;
    private List<PaymentItemResponseVo> payments;
    private List<PaymentRelatedOrderResponseVo> orderDetails;

    public PaymentResponseVo(Payment payment) {
        BeanUtils.copyProperties(payment,this);
        this.setOrderDetails(payment.getCustomerOrders());
        this.setPayments(payment.getPaymentItems());

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

    public void setOrderDetails(List<CustomerOrder> customerOrders) {
        List<PaymentRelatedOrderResponseVo> temp = new ArrayList<>();
        customerOrders.forEach(customerOrder -> {
            PaymentRelatedOrderResponseVo responseVo = new PaymentRelatedOrderResponseVo(customerOrder);
            temp.add(responseVo);
        });
        this.orderDetails = temp;
    }
}
