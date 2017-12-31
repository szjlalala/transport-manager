package com.tms.controller.vo.request;

import com.tms.model.CustomerOrder;
import com.tms.model.DeliverOrder;
import com.tms.model.Payment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "查询运单详情请求参数")
public class QueryDeliverOrderRequestVo implements Serializable {
    @ApiModelProperty(value = "运单状态", name = "state")
    private DeliverOrder.DeliverOrderState state;
    @ApiModelProperty(value = "运单号", name = "deliverOrderNo")
    private String deliverOrderNo;
    @ApiModelProperty(value = "开始时间", name = "startTime")
    private Date startTime;
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;
    @ApiModelProperty(value = "发货人", name = "from")
    private QueryLocationQuestVo from;
    @ApiModelProperty(value = "收货人", name = "to")
    private QueryLocationQuestVo to;

    public DeliverOrder.DeliverOrderState getState() {
        return state;
    }

    public void setState(DeliverOrder.DeliverOrderState state) {
        this.state = state;
    }

    public String getDeliverOrderNo() {
        return deliverOrderNo;
    }

    public void setDeliverOrderNo(String deliverOrderNo) {
        this.deliverOrderNo = deliverOrderNo;
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

    public QueryDeliverOrderRequestVo() {
    }

}
