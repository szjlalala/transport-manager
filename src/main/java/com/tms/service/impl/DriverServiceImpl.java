package com.tms.service.impl;


import com.tms.common.Constant;
import com.tms.controller.vo.request.DriverRequestDto;
import com.tms.controller.vo.request.QueryDriverRequestVo;
import com.tms.controller.vo.response.DriverResponseVo;
import com.tms.model.Driver;
import com.tms.model.SysDriver;
import com.tms.repository.DriverRepository;
import com.tms.repository.SysDriverRepository;
import com.tms.service.DriverService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private SysDriverRepository sysDriverRepository;

    @Override
    public void createDriver(DriverRequestDto driverRequestDto) {
        Driver driver = new Driver(driverRequestDto);
        driver.preInsert();
        driverRepository.save(driver);
        SysDriver sysDriver = new SysDriver(driver, driverRequestDto.getStatus());
        sysDriver.preInsert();
        sysDriverRepository.save(sysDriver);
    }

    @Override
    public void updateDriver(DriverRequestDto driverRequestVo) {
        Driver driver = driverRepository.findOne(driverRequestVo.getId());
        BeanUtils.copyProperties(driverRequestVo, driver, "id");
        driver.preUpdate();
        driverRepository.save(driver);
        SysDriver sysDriver = sysDriverRepository.findByDriver(driver);
        sysDriver.preUpdate();
        sysDriver.setDriverState(driverRequestVo.getStatus());
        sysDriverRepository.save(sysDriver);
    }

    @Override
    public Page<DriverResponseVo> queryDriver(QueryDriverRequestVo queryDriverRequestVo, Pageable page) {
        Page domainPage = driverRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (!StringUtils.isEmpty(queryDriverRequestVo.getBankCard())) {
                predicate.add(criteriaBuilder.equal(root.get("bankCard"), queryDriverRequestVo.getBankCard()));
            }
            if (!StringUtils.isEmpty(queryDriverRequestVo.getDrivingLicense())) {
                predicate.add(criteriaBuilder.equal(root.get("drivingLicense"), queryDriverRequestVo.getDrivingLicense()));
            }
            if (!StringUtils.isEmpty(queryDriverRequestVo.getEducation())) {
                predicate.add(criteriaBuilder.equal(root.get("education"), queryDriverRequestVo.getEducation()));
            }
            if (queryDriverRequestVo.getGender() != null) {
                predicate.add(criteriaBuilder.equal(root.get("gender").as(Constant.Gender.class), queryDriverRequestVo.getGender()));
            }
            if (queryDriverRequestVo.getId() != null) {
                predicate.add(criteriaBuilder.equal(root.get("id"), queryDriverRequestVo.getId()));
            }
            if (!StringUtils.isEmpty(queryDriverRequestVo.getIdCard())) {
                predicate.add(criteriaBuilder.equal(root.get("idCard"), queryDriverRequestVo.getIdCard()));
            }
            if (!StringUtils.isEmpty(queryDriverRequestVo.getName())) {
                predicate.add(criteriaBuilder.equal(root.get("name"), queryDriverRequestVo.getName()));
            }
            if (!StringUtils.isEmpty(queryDriverRequestVo.getPhone())) {
                predicate.add(criteriaBuilder.equal(root.get("phone"), queryDriverRequestVo.getPhone()));
            }
            if (queryDriverRequestVo.getStartTime() != null) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), queryDriverRequestVo.getStartTime()));
            }
            if (queryDriverRequestVo.getEndTime() != null) {
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), queryDriverRequestVo.getEndTime()));
            }
            if (queryDriverRequestVo.getStatus() != null) {
                    predicate.add(criteriaBuilder.equal(root.join("sysDriver").get("driverState"), queryDriverRequestVo.getStatus()));
            }
            return criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        }, page);

        Page voPage = domainPage.map((Converter<Driver, DriverResponseVo>) driver -> {
            DriverResponseVo driverResponseVo = new DriverResponseVo(driver);
            driverResponseVo.setStatus(sysDriverRepository.findByDriver(driver).getDriverState());
            return driverResponseVo;
        });
        return voPage;
    }

    @Override
    public DriverResponseVo queryDriver(Long id) {
        Driver driver = driverRepository.findOne(id);
        DriverResponseVo driverResponseVo = new DriverResponseVo(driver);
        driverResponseVo.setStatus(sysDriverRepository.findByDriver(driver).getDriverState());
        return driverResponseVo;
    }
}
