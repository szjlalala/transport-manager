package com.tms.controller.vo.request;

import com.tms.common.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "查询订单详情请求参数")
public class QueryOrderDto implements Serializable {
    @ApiModelProperty(value = "订单状态", name = "state")
    private Constant.OrderState state;
    @ApiModelProperty(value = "订单号", name = "id")
    private String id;
    @ApiModelProperty(value = "用户Id", name = "customerId")
    private Long customerId;
    @ApiModelProperty(value = "开始时间", name = "startTime")
    private Date startTime;
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;
    @ApiModelProperty(value = "支付状态", name = "payState")
    private Constant.PayState payState;
    @ApiModelProperty(value = "支付方式", name = "payType")
    private Constant.PayType payType;
    @ApiModelProperty(value = "发货人", name = "from")
    private QueryLocationQuestVo from;
    @ApiModelProperty(value = "收货人", name = "to")
    private QueryLocationQuestVo to;

}
