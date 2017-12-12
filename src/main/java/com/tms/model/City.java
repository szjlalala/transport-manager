package com.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class City implements Serializable {
    @Id
    private String code;
    private String cityName;
    private String provinceCode;

    public City() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
