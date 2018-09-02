package com.tms.controller.vo.response;

import com.vividsolutions.jts.geom.Point;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TraceResponseVo implements Serializable {
    @ApiModelProperty(value = "坐标", name = "geo")
    private Point geo;
    /*@ApiModelProperty(value = "车辆", name = "vehicle")
    private VehicleResponseVo vehicle;
    @ApiModelProperty(value = "司机", name = "driver")
    private DriverResponseVo driver;*/

}
