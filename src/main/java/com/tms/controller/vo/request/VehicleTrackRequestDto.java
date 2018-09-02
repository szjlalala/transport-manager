package com.tms.controller.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/9/2.
 */
@Data
@NoArgsConstructor
@ApiModel(value = "添加位置请求")
public class VehicleTrackRequestDto {
    private Long id;
    private double x;
    private double y;
}
