package com.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
public class Province implements Serializable {
    @Id
    private String code;
    private String provinceName;

    public Province() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
