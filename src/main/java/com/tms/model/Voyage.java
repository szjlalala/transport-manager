package com.tms.model;

import javax.persistence.*;

@Entity
public class Voyage extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn
    private SysCode mediaType;//交通工具大类别 airplane vehicle ship
    @OneToOne
    @JoinColumn
    private SysCode mediaSubType;//子类别 卡车 货车。。。
    private String plateNumber;//牌号
    private String driveLicense;//行驶证
    private String operatorLicense;//运营证

    public Voyage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SysCode getMediaType() {
        return mediaType;
    }

    public void setMediaType(SysCode mediaType) {
        this.mediaType = mediaType;
    }

    public SysCode getMediaSubType() {
        return mediaSubType;
    }

    public void setMediaSubType(SysCode mediaSubType) {
        this.mediaSubType = mediaSubType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriveLicense() {
        return driveLicense;
    }

    public void setDriveLicense(String driveLicense) {
        this.driveLicense = driveLicense;
    }

    public String getOperatorLicense() {
        return operatorLicense;
    }

    public void setOperatorLicense(String operatorLicense) {
        this.operatorLicense = operatorLicense;
    }
}
