package com.tms.model;


import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
public class Trace extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Point")
    private Point geo;

    @OneToOne
    @JoinColumn
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn
    private Driver driver;


    public Trace() {
    }

    public Point getGeo() {
        return geo;
    }

    public void setGeo(Point geo) {
        this.geo = geo;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
