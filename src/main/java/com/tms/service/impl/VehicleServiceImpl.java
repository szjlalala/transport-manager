package com.tms.service.impl;

import com.tms.controller.vo.request.VehicleRequestDto;
import com.tms.controller.vo.request.QueryVehicleRequestVo;
import com.tms.controller.vo.request.VehicleTrackRequestDto;
import com.tms.controller.vo.response.TraceResponseVo;
import com.tms.controller.vo.response.VehicleResponseVo;
import com.tms.model.Driver;
import com.tms.model.Trace;
import com.tms.model.Vehicle;
import com.tms.repository.DriverRepository;
import com.tms.repository.SysCodeRepository;
import com.tms.repository.TraceRepository;
import com.tms.repository.VehicleRepository;
import com.tms.service.VehicleService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
//import org.geotools.geometry.jts.JTSFactoryFinder;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private SysCodeRepository sysCodeRepository;
    @Autowired
    private TraceRepository traceRepository;
//    private GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory( null );



    @Override
    public void createVehicle(VehicleRequestDto vehicleRequestDto, List<Driver> drivers) {
        Vehicle vehicle = new Vehicle(vehicleRequestDto);
        vehicle.setRemainLoads(vehicle.getLoads());
        vehicle.setDriverList(drivers);
//        vehicle.setVehicleType(sysCodeRepository.findByCode(vehicleRequestDto.getVehicleType()));
//        vehicle.setVehicleSubType(sysCodeRepository.findByCode(vehicleRequestDto.getVehicleSubType()));
        vehicle.preInsert();
        vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehicle(VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle =vehicleRepository.findOne(vehicleRequestDto.getId());
        BeanUtils.copyProperties(vehicleRequestDto,vehicle);
//        vehicle.setVehicleType(sysCodeRepository.findByCode(vehicleRequestDto.getVehicleType()));
//        vehicle.setVehicleSubType(sysCodeRepository.findOne(vehicleRequestDto.getVehicleSubType()));
        List<Driver> drivers = new ArrayList<>();
        vehicleRequestDto.getDrivers().stream().forEach(driverIdPair -> {
            Driver driver = driverRepository.findOne(driverIdPair.getId());
            if(driver != null){
                drivers.add(driver);
            }
        });
        vehicle.setDriverList(drivers);
        vehicle.preUpdate();
        vehicleRepository.save(vehicle);
    }

    @Override
    public Page<VehicleResponseVo> queryVehicle(QueryVehicleRequestVo vehicleRequestVo, Pageable page) {
        Page domainPage = vehicleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (!StringUtils.isEmpty(vehicleRequestVo.getBrand())) {
                predicate.add(criteriaBuilder.equal(root.get("brand"), vehicleRequestVo.getBrand()));
            }
            if (vehicleRequestVo.getVehicleType() != null) {
                predicate.add(criteriaBuilder.equal(root.get("vehicleType"), vehicleRequestVo.getVehicleType()));
            }
            if (vehicleRequestVo.getVehicleSubType() != null) {
                predicate.add(criteriaBuilder.equal(root.get("vehicleSubType"), vehicleRequestVo.getVehicleSubType()));
            }
            if (!StringUtils.isEmpty(vehicleRequestVo.getPlateNumber())) {
                predicate.add(criteriaBuilder.equal(root.get("plateNumber"), vehicleRequestVo.getPlateNumber()));
            }
            if (!StringUtils.isEmpty(vehicleRequestVo.getDriveLicense())) {
                predicate.add(criteriaBuilder.equal(root.get("driveLicense"), vehicleRequestVo.getDriveLicense()));
            }
            if (!StringUtils.isEmpty(vehicleRequestVo.getOperatorLicense())) {
                predicate.add(criteriaBuilder.equal(root.get("operatorLicense"), vehicleRequestVo.getOperatorLicense()));
            }
            if (vehicleRequestVo.getState() != null) {
                predicate.add(criteriaBuilder.equal(root.get("state"), vehicleRequestVo.getState()));
            }
            if (!StringUtils.isEmpty(vehicleRequestVo.getCompany())) {
                predicate.add(criteriaBuilder.equal(root.get("company"), vehicleRequestVo.getCompany()));
            }
            if (!StringUtils.isEmpty(vehicleRequestVo.getOwner())) {
                predicate.add(criteriaBuilder.equal(root.get("owner"), vehicleRequestVo.getOwner()));
            }
            if (!StringUtils.isEmpty(vehicleRequestVo.getOwnerPhone())) {
                predicate.add(criteriaBuilder.equal(root.get("ownerPhone"), vehicleRequestVo.getOwnerPhone()));
            }
            if (vehicleRequestVo.getLoads() != null) {
                predicate.add(criteriaBuilder.equal(root.get("loads"), vehicleRequestVo.getLoads()));
            }
            if (vehicleRequestVo.getStartTime() != null) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), vehicleRequestVo.getStartTime()));
            }
            if (vehicleRequestVo.getEndTime() != null) {
                predicate.add(criteriaBuilder.lessThan(root.get("createTime").as(Date.class), vehicleRequestVo.getEndTime()));
            }
            return criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        }, page);

        Page voPage = domainPage.map((Converter<Vehicle, VehicleResponseVo>) vehicle -> new VehicleResponseVo(vehicle));
        return voPage;
    }

    @Override
    public List<TraceResponseVo> queryTrace(Long vehicleId, Date start, Date end) {
        List traces = traceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            predicate.add(criteriaBuilder.equal(root.join("vehicle").get("id"), vehicleId));
//            predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), start));
//            predicate.add(criteriaBuilder.lessThan(root.get("createTime").as(Date.class), end));
            return criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        });
        List<TraceResponseVo> responseVos = new ArrayList<>();
        traces.forEach(trace -> {
            TraceResponseVo responseVo = new TraceResponseVo();
            BeanUtils.copyProperties(trace, responseVo);
            responseVos.add(responseVo);
        });
        return responseVos;
    }

    @Override
    public List<TraceResponseVo> queryTrace(String plateNumber, Date start, Date end) {
        List traces = traceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            predicate.add(criteriaBuilder.equal(root.join("vehicle").get("plateNumber"), plateNumber));
            return criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        });
        List<TraceResponseVo> responseVos = new ArrayList<>();
        traces.forEach(trace -> {
            TraceResponseVo responseVo = new TraceResponseVo();
            BeanUtils.copyProperties(trace, responseVo);
            responseVos.add(responseVo);
        });
        return responseVos;
    }


    @Override
    public VehicleResponseVo queryVehicle(Long id) {
        return new VehicleResponseVo(vehicleRepository.findOne(id));
    }
    @Override
    public VehicleResponseVo queryVehicle(String plateNumber) {
        return new VehicleResponseVo(vehicleRepository.findByPlateNumber(plateNumber));
    }


    @Override
    public void pushTrace(VehicleTrackRequestDto vehicleTrackRequestDto) {
        Vehicle vehicle = vehicleRepository.findOne(vehicleTrackRequestDto.getId());
        Trace trace = new Trace();
        trace.setVehicle(vehicle);
        Point point = new GeometryFactory().createPoint(new Coordinate( vehicleTrackRequestDto.getX(), vehicleTrackRequestDto.getY()));
//        Point point = geometryFactory.createPoint( new Coordinate( vehicleTrackRequestDto.getX(), vehicleTrackRequestDto.getY() ) );
//        trace.setGeo(new Point(vehicleTrackRequestDto.getX(), vehicleTrackRequestDto.getY()));
        trace.setGeo(point);
        trace.preInsert();
        traceRepository.save(trace);
    }
}
