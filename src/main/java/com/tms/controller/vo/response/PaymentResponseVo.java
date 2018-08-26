package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.controller.vo.BaseVo;
import com.tms.controller.vo.request.PostOrderDto;
import com.tms.model.Customer;
import com.tms.model.CustomerOrder;
import com.tms.model.Payment;
import com.tms.model.PaymentItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "查询订单详情返回结果")
public class PaymentResponseVo extends BaseVo implements Serializable {
    private Long id;
    private Constant.PayState payState;
    private Date finishTime;//完成时间
    private Date expireTime;//过期时间
    private BigDecimal originalPrice;//订单应付金额
    private BigDecimal payPrice;//订单实付金额
    private BigDecimal deliverPrice;//运费
    private BigDecimal insurancePrice;//保价金额
    private Constant.PayType payType;
    private Customer customer;
    private List<PaymentItem> paymentItems;
    private List<CustomerOrder> customerOrders;

    public PaymentResponseVo(Payment payment) {
        BeanUtils.copyProperties(payment,this);
    }
}
