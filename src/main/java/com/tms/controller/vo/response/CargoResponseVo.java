package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.model.Cargo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class CargoResponseVo implements Serializable {
    @ApiModelProperty(value = "货物名称", name = "name")
    private String name;
    @ApiModelProperty(value = "数量", name = "count")
    private Long count;
    @ApiModelProperty(value = "重量单位kg", name = "weight")
    private BigDecimal weight;
    @ApiModelProperty(value = "体积单位立方", name = "volume")
    private BigDecimal volume;
    @ApiModelProperty(value = "价值", name = "price")
    private BigDecimal price;
    @ApiModelProperty(value = "货物类型", name = "cargoType")
    private Constant.CargoType cargoType;
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

}
