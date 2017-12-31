package com.tms.service;

import com.tms.controller.vo.request.CreateVehicleRequestVo;
import com.tms.controller.vo.request.QueryVehicleRequestVo;
import com.tms.controller.vo.response.TraceResponseVo;
import com.tms.controller.vo.response.VehicleResponseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface VehicleService {
    void createVehicle(CreateVehicleRequestVo createVehicleRequestVo);

    void updateVehicle(CreateVehicleRequestVo createVehicleRequestVo);

    Page<VehicleResponseVo> queryVehicle(QueryVehicleRequestVo vehicleRequestVo, Pageable page);

    List<TraceResponseVo> queryTrace(Long vehicleId, Date start, Date end);
}
