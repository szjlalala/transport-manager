package com.tms.controller.vo.response;

import com.tms.common.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
public class PaymentItemResponseVo implements Serializable {
    @ApiModelProperty(value = "支付状态", name = "payState")
    private Constant.PayState payState;
    @ApiModelProperty(value = "应支付价格", name = "payPrice")
    private BigDecimal payPrice;
    @ApiModelProperty(value = "支付方式", name = "payChannel")
    private Constant.PayChannel payChannel;
    @ApiModelProperty(value = "交易完成时间", name = "finishTime")
    private Date finishTime;

}
