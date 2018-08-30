package com.tms.model;

import com.tms.common.Constant;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by song on 2018/8/30.
 */
@Data
public class VehicleSituation {
    private Long id;
    private String vehicleType;
    private String plateNumber;//牌号
    private String driveLicense;//行驶证
    private String operatorLicense;//运营证
    private Constant.VehicleState state = Constant.VehicleState.ON;
    private String brand;//商标
    private String company;//所属公司
    private String owner;//车主
    private String ownerPhone;//车主电话
    private Float loads;//载重量
    private Driver driver;
    private AxisPair location;
    private List<AxisPair> track;
    @Data
    public static class Driver{
        String name;
        String phone;
    }

}
