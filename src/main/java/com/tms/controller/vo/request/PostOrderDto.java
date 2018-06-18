package com.tms.controller.vo.request;

import com.tms.common.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "创建订单请求参数")
public class PostOrderDto implements Serializable {
    @ApiModelProperty(value = "发货地址", name = "from", required = true)
    private PostLocationDto from;
    @ApiModelProperty(value = "订单", name = "orders", required = true)
    private List<OrderDto> orders;
    @ApiModelProperty(value = "支付信息", name = "payment", required = true)
    private PaymentDto payment;
    private Long customerId;


    @Data
    @NoArgsConstructor
    public static class OrderDto implements Serializable {
        @ApiModelProperty(value = "收货地址", name = "to", required = true)
        private PostLocationDto to;
        @ApiModelProperty(value = "货物列表", name = "cargoes", required = true)
        private List<CargoDto> cargoes;
        @ApiModelProperty(value = "支付信息", name = "payment", required = true)
        private PaymentDto payment;
        @ApiModelProperty(value = "距离", name = "distance", required = true)
        private Long distance;
    }

    @Data
    @NoArgsConstructor
    public static class PaymentDto implements Serializable {
        @ApiModelProperty(value = "运费", name = "deliverPrice", required = true)
        private BigDecimal deliverPrice;
        @ApiModelProperty(value = "保价", name = "insurancePrice", required = true)
        private BigDecimal insurancePrice;
        @ApiModelProperty(value = "支付金额", name = "payPrice", example = "1000")
        private BigDecimal payPrice;
        @ApiModelProperty(value = "支付方式", name = "payType", required = true)
        private Constant.PayType payType;
    }

    @Data
    @NoArgsConstructor
    public static class PostLocationDto implements Serializable {
        @ApiModelProperty(value = "姓名", name = "name", required = true)
        private String name;
        @ApiModelProperty(value = "电话", name = "phone", required = true)
        private String phone;
        @ApiModelProperty(value = "区编码", name = "district", required = true, example = "110101")
        private Long district;
        @ApiModelProperty(value = "详细地址", name = "address", required = true)
        private String address;
        @ApiModelProperty(value = "X坐标", name = "x", required = true)
        private Double x;
        @ApiModelProperty(value = "Y坐标", name = "y", required = true)
        private Double y;
    }

    @Data
    @NoArgsConstructor
    public static class CargoDto implements Serializable {
        @ApiModelProperty(value = "货物名称", name = "name", required = true)
        private String name;
        @ApiModelProperty(value = "重量单位kg", name = "weight", required = true)
        private BigDecimal weight;
        @ApiModelProperty(value = "体积单位立方", name = "volume", required = true)
        private BigDecimal volume;
        @ApiModelProperty(value = "价值", name = "price")
        private BigDecimal price;
        @ApiModelProperty(value = "货物类型", name = "cargoType", required = true)
        private Constant.CargoType cargoType;
        @ApiModelProperty(value = "货物备注", name = "remark")
        private String remark;
    }
}





