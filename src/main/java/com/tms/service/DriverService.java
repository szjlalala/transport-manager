package com.tms.service;

import com.tms.controller.vo.request.DriverRequestDto;
import com.tms.controller.vo.request.QueryDriverRequestVo;
import com.tms.controller.vo.response.DriverResponseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverService {
    void createDriver(DriverRequestDto driverRequestDto);

    void updateDriver(DriverRequestDto driverRequestVo);

    Page<DriverResponseVo> queryDriver(QueryDriverRequestVo queryDriverRequestVo, Pageable page);

    DriverResponseVo queryDriver(Long id);
}
