package com.tms.controller.vo.request;

import com.tms.common.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class SysDriverRequestVo {
    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
    @ApiModelProperty(value = "左后登录ip", name = "lastIp")
    private String lastIp;
    @ApiModelProperty(value = "最后登录时间", name = "lastLoginTime")
    private Date lastLoginTime;
    @ApiModelProperty(value = "司机状态", name = "driverState")
    private Constant.DriverState driverState;

}
