package com.tms.model;

import com.tms.common.Constant;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SysDriver extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String lastIp;
    private Date lastLoginTime;
    private Constant.DriverState driverState;
    private String token;
    @OneToOne
    @JoinColumn
    private Driver driver;

    public SysDriver() {
    }

    public SysDriver(Driver driver) {
        this.driver = driver;
        this.userName = driver.getPhone();
        this.password = MD5Encoder.encode("123456".getBytes());
        this.driverState = Constant.DriverState.OFF;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Constant.DriverState getDriverState() {
        return driverState;
    }

    public void setDriverState(Constant.DriverState driverState) {
        this.driverState = driverState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
