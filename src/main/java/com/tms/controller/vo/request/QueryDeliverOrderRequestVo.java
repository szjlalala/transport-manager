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
@ApiModel(value = "查询运单详情请求参数")
public class QueryDeliverOrderRequestVo implements Serializable {
    @ApiModelProperty(value = "运单状态", name = "state")
    private Constant.OrderState deliverOrderState;
    @ApiModelProperty(value = "运单号", name = "id")
    private String id;
    @ApiModelProperty(value = "开始时间", name = "startTime")
    private Date startTime;
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;
    @ApiModelProperty(value = "发货人", name = "from")
    private QueryLocationQuestVo from;
    @ApiModelProperty(value = "收货人", name = "to")
    private QueryLocationQuestVo to;

}
