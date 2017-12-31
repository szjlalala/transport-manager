package com.tms.controller.vo.request;

import com.tms.model.SysDriver;

import java.util.Date;

public class SysDriverRequestVo {
    private String userName;
    private String password;
    private String lastIp;
    private Date lastLoginTime;
    private SysDriver.DriverState driverState;

    public SysDriverRequestVo() {
    }

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
}
