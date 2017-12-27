package com.tms.controller.vo.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class DriverResponseVo implements Serializable {
    @ApiModelProperty(value = "司机id", name = "id")
    private Long id;

    public DriverResponseVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
