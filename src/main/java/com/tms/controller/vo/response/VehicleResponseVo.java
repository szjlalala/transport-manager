package com.tms.controller.vo.response;

import com.tms.model.SysCode;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class VehicleResponseVo implements Serializable {
    @ApiModelProperty(value = "id", name = "id")
    private Long id;
    @ApiModelProperty(value = "大类别", name = "mediaType")
    private SysCode mediaType;
    @ApiModelProperty(value = "子类别", name = "mediaSubType")
    private SysCode mediaSubType;
    @ApiModelProperty(value = "车牌号", name = "plateNumber")
    private String plateNumber;
    @ApiModelProperty(value = "行驶证", name = "driveLicense")
    private String driveLicense;
    @ApiModelProperty(value = "运营证", name = "operatorLicense")
    private String operatorLicense;

    public VehicleResponseVo() {
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
