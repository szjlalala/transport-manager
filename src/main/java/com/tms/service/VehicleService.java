package com.tms.service;

import com.tms.controller.vo.request.VehicleRequestDto;
import com.tms.controller.vo.request.QueryVehicleRequestVo;
import com.tms.controller.vo.request.VehicleTrackRequestDto;
import com.tms.controller.vo.response.TraceResponseVo;
import com.tms.controller.vo.response.VehicleResponseVo;
import com.tms.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface VehicleService {
    void createVehicle(VehicleRequestDto vehicleRequestDto, List<Driver> drivers);

    void updateVehicle(VehicleRequestDto vehicleRequestDto);

    Page<VehicleResponseVo> queryVehicle(QueryVehicleRequestVo vehicleRequestVo, Pageable page);

    List<TraceResponseVo> queryTrace(Long vehicleId, Date start, Date end);

    VehicleResponseVo queryVehicle(Long id);

    void pushTrace(VehicleTrackRequestDto vehicleTrackRequestDto);
}
