package com.tms.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class CreateOrderDetailRequestVo implements Serializable {
    @ApiModelProperty(value = "发货地址", name = "from",required=true)
    private CreateOrderLocationRequestVo from;
    @ApiModelProperty(value = "收货地址", name = "to",required=true)
    private CreateOrderLocationRequestVo to;
    @ApiModelProperty(value = "货物列表", name = "cargoes",required=true)
    private List<CreateOrderCargoRequestVo> cargoes;

    public CreateOrderDetailRequestVo() {
    }

    public CreateOrderLocationRequestVo getFrom() {
        return from;
    }

    public void setFrom(CreateOrderLocationRequestVo from) {
        this.from = from;
    }

    public CreateOrderLocationRequestVo getTo() {
        return to;
    }

    public void setTo(CreateOrderLocationRequestVo to) {
        this.to = to;
    }

    public List<CreateOrderCargoRequestVo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<CreateOrderCargoRequestVo> cargoes) {
        this.cargoes = cargoes;
    }
}
