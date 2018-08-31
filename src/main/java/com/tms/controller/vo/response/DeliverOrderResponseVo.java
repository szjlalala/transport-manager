package com.tms.controller.vo.response;

import com.tms.common.Constant;
import com.tms.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class DeliverOrderResponseVo implements Serializable {
    @ApiModelProperty(value = "运单号", name = "id")
    private String id;
    @ApiModelProperty(value = "开始地址", name = "from")
    private LocationResponseVo from;
    @ApiModelProperty(value = "结束地址", name = "to")
    private LocationResponseVo to;
    @ApiModelProperty(value = "开始时间", name = "startTime")
    private Date startTime;
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;
    @ApiModelProperty(value = "司机", name = "driver")
    private DriverResponseVo driver;
    @ApiModelProperty(value = "车辆", name = "vehicle")
    private VehicleResponseVo vehicle;
    @ApiModelProperty(value = "运单状态", name = "deliverOrderState")
    private Constant.OrderState deliverOrderState;
    @ApiModelProperty(value = "序列", name = "sequence")
    private Integer sequence;
    @ApiModelProperty(value = "距离", name = "distance")
    private Long distance;
    @ApiModelProperty(value = "用户订单", name = "customerOrder")
    private OrderListResponseVo customerOrder;
    @ApiModelProperty(value = "货物", name = "cargoes")
    private List<CargoResponseVo> cargoes;

    public DeliverOrderResponseVo(DeliverOrder deliverOrder) {
        BeanUtils.copyProperties(deliverOrder,this);
        this.id = deliverOrder.getDeliverOrderNo();
        this.setFrom(deliverOrder.getFrom());
        this.setTo(deliverOrder.getTo());
        this.setVehicle(deliverOrder.getVehicle());
        this.setCustomerOrder(deliverOrder.getCustomerOrder());
        this.setDriver(deliverOrder.getDriver());
        this.setCargoes(deliverOrder.getCargoes());
    }

    private void setDriver(Driver driver) {
        DriverResponseVo driverResponseVo = new DriverResponseVo();
        if (driver != null)
            BeanUtils.copyProperties(driver, driverResponseVo);
        this.driver = driverResponseVo;
    }

    private void setCustomerOrder(CustomerOrder customerOrder) {
        OrderListResponseVo orderListResponseVo = new OrderListResponseVo(customerOrder);
        this.customerOrder = orderListResponseVo;
    }

    private void setVehicle(Vehicle vehicle) {
        VehicleResponseVo vehicleResponseVo = new VehicleResponseVo();
        if (vehicle != null)
            BeanUtils.copyProperties(vehicle, vehicleResponseVo);
        this.vehicle = vehicleResponseVo;
    }

    public void setCargoes(List<DeliverCargo> cargoes) {
        List<CargoResponseVo> temp = new ArrayList<>();
        cargoes.forEach(cargo -> {
            CargoResponseVo cargoResponseVo = new CargoResponseVo();
            BeanUtils.copyProperties(cargo, cargoResponseVo);
            temp.add(cargoResponseVo);
        });
        this.cargoes = temp;
    }

    public void setFrom(Location from) {
        this.from = LocationResponseVo.genLocationResponseVoFromLocation(from);
    }

    public void setTo(Location to) {
        this.to = LocationResponseVo.genLocationResponseVoFromLocation(to);
    }
}
