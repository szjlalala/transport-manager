package com.tms.controller.vo.response;

import com.tms.model.SysDriver;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SysDriverResponseVo implements Serializable {
    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
    @ApiModelProperty(value = "左后登录ip", name = "lastIp")
    private String lastIp;
    @ApiModelProperty(value = "最后登录时间", name = "lastLoginTime")
    private Date lastLoginTime;
    @ApiModelProperty(value = "司机状态", name = "driverState")
    private SysDriver.DriverState driverState;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public SysDriver.DriverState getDriverState() {
        return driverState;
    }

    public void setDriverState(SysDriver.DriverState driverState) {
        this.driverState = driverState;
    }

    public SysDriverResponseVo() {
    }
}
