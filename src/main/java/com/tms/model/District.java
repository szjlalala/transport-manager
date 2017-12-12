package com.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class District implements Serializable {
    @Id
    private String code;
    private String districtName;
    private String cityCode;

    public District() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
