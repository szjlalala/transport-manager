package com.tms.model;

import com.tms.controller.vo.response.TraceResponseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/8/30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AxisPair {
    Double x;
    Double y;

    public AxisPair(TraceResponseVo traceResponseVo) {
        this.x = traceResponseVo.getGeo().getX();
        this.y = traceResponseVo.getGeo().getY();
    }
}
