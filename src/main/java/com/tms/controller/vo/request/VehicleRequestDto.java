package com.tms.controller.vo.request;

import com.tms.common.Constant;
import com.tms.model.Vehicle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@ApiModel(value = "创建车辆请求参数")
public class VehicleRequestDto implements Serializable {
    @ApiModelProperty(value = "id", name = "id")
    private Long id;
    @ApiModelProperty(value = "车型", name = "vehicleType")
    private String vehicleType;
    @ApiModelProperty(value = "品牌", name = "vehicleSubType")
    private Long vehicleSubType;
    @ApiModelProperty(value = "牌号", name = "plateNumber")
    private String plateNumber;
    @ApiModelProperty(value = "行驶证", name = "driveLicense")
    private String driveLicense;
    @ApiModelProperty(value = "运营证", name = "operatorLicense")
    private String operatorLicense;
    @ApiModelProperty(value = "车辆状态", name = "state")
    private Constant.VehicleState state;
    @ApiModelProperty(value = "商标", name = "brand")
    private String brand;
    @ApiModelProperty(value = "所属公司", name = "company")
    private String company;
    @ApiModelProperty(value = "车主", name = "owner")
    private String owner;
    @ApiModelProperty(value = "车主电话", name = "ownerPhone")
    private String ownerPhone;
    @ApiModelProperty(value = "载重量", name = "loads")
    private Float loads;
}
