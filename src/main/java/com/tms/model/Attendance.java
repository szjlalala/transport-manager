package com.tms.model;

import com.tms.common.Constant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Attendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn
    private Vehicle vehicle;
    @OneToOne
    @JoinColumn
    private Driver driver;
    private Constant.AttendanceType type;
    private Date createTime;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Constant.AttendanceType getType() {
        return type;
    }

    public void setType(Constant.AttendanceType type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
