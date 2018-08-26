package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.PostOrderDto;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
@Data
public class Location extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Constant.LocationType locationType;
    private String name;
    private String phone;
    private Long province;
    private Long city;
    private Long district;
    private String address;
    @Column(columnDefinition = "POINT")
    private Point geo;

    public Location(PostOrderDto.PostLocationDto locationDto) {
        BeanUtils.copyProperties(locationDto, this);
        this.address = locationDto.getAddress().getStr();
        this.geo = new GeometryFactory().createPoint(new Coordinate(locationDto.getAddress().getX(), locationDto.getAddress().getY()));
        this.city = Long.parseLong(locationDto.getDistrict().toString().substring(0, 4) + "00");
        this.province = Long.parseLong(locationDto.getDistrict().toString().substring(0, 2) + "0000");
    }

    public Location() {
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getDistrict() {
        return district;
    }

    public void setDistrict(Long district) {
        this.district = district;
    }

    public Constant.LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(Constant.LocationType locationType) {
        this.locationType = locationType;
    }

    public Point getGeo() {
        return geo;
    }

    public void setGeo(Point geo) {
        this.geo = geo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
