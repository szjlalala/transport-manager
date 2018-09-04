package com.tms.controller.vo.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by song on 2018/9/3.
 */
@Data
public class SplitDto {
    String id;
    List<SplitTuple> splitCubes;
    @Data
    public static class SplitTuple{
        private BigDecimal weight;//重量单位kg
        private BigDecimal volume;//体积单位立方
        private BigDecimal deliverPrice;//价值
    }

}
