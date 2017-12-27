package com.tms.controller.vo.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CustomerResponseVo implements Serializable {
    @ApiModelProperty(value = "用户id", name = "id")
    private Long id;

    public CustomerResponseVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
