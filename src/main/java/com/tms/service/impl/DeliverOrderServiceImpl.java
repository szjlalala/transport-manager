package com.tms.service.impl;

import com.tms.common.BizException;
import com.tms.common.Constant;
import com.tms.common.Results;
import com.tms.controller.vo.request.QueryDeliverOrderRequestVo;
import com.tms.controller.vo.request.SplitDto;
import com.tms.controller.vo.response.DeliverOrderResponseVo;
import com.tms.model.*;
import com.tms.repository.DeliverOrderRepository;
import com.tms.repository.DriverRepository;
import com.tms.repository.SysCodeRepository;
import com.tms.repository.VehicleRepository;
import com.tms.service.CustomerOrderService;
import com.tms.service.DeliverOrderService;
import com.tms.service.MQProducer;
import com.tms.service.RouteService;
import com.tms.util.IDGen;
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
import java.util.stream.Collectors;

@Service
public class DeliverOrderServiceImpl implements DeliverOrderService {
    @Autowired
    private RouteService routeService;
    @Autowired
    private DeliverOrderRepository deliverOrderRepository;
    @Autowired
    private SysCodeRepository sysCodeRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private MQProducer mqProducer;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Override
    public List<DeliverOrder> createDeliverOrder(CustomerOrder customerOrder) {
        List<DeliverOrder> deliverOrders = routeService.designRoute(customerOrder);
        for (DeliverOrder deliverOrder : deliverOrders) {
            deliverOrderRepository.save(deliverOrder);
        }
        return deliverOrders;
    }

    @Override
    public void spreadDeliverOrder(DeliverOrder deliverOrder) {
        //根据当前调度模式进行派单
        SysCode sysCode = sysCodeRepository.findByCode("allocate_type");
        Constant.AllocateType allocateType = Constant.AllocateType.values()[Integer.parseInt(sysCode.getaValue())];
        switch (allocateType) {
            case AUTO:
                mqProducer.sendDeliverOrderToRouter(deliverOrder);
                break;
            case MANUAL:
                mqProducer.sendDeliverOrderToManager(deliverOrder);
                break;
            case COMPETE:
                mqProducer.sendDeliverOrderToDriver(deliverOrder);
                break;
        }
    }

    @Override
    public DeliverOrder allocateVehicleAndDriver(String deliverOrderNo, Long voyageId, String driverId) {
        DeliverOrder deliverOrder = deliverOrderRepository.findByDeliverOrderNo(deliverOrderNo);
        if (deliverOrder == null) {
            throw new BizException(Results.ErrorCode.ORDER_NOT_EXIST);
        }
        Driver driver = driverRepository.findOne(driverId);
        if (driver == null) {
            throw new BizException(Results.ErrorCode.DRIVER_NOT_EXIST);
        }
        Vehicle vehicle = vehicleRepository.findOne(voyageId);

        if (vehicle == null) {
            throw new BizException(Results.ErrorCode.VEHICLE_NOT_EXIST);
        }
        Float remainLoads = vehicle.getRemainLoads();
        Float cargoWeight = new Float(deliverOrder.getCargoes().get(0).getWeight().toPlainString());
        //如果货物重量大于载重
        if(cargoWeight.compareTo(remainLoads) > 0){
            vehicle.setRemainLoads(new Float(0.0));
        }else{
            vehicle.setRemainLoads(remainLoads-cargoWeight);
        }
        deliverOrder.setDriver(driver);
        deliverOrder.setVehicle(vehicle);
        deliverOrder.preUpdate();
        deliverOrder.setDeliverOrderState(Constant.OrderState.NOT_RECEIVED);
        deliverOrder.getCustomerOrder().setState(Constant.OrderState.NOT_RECEIVED);
        deliverOrder.setDistributTime(new Date());
        deliverOrderRepository.save(deliverOrder);
        customerOrderService.startCustomerOrderDetail(deliverOrder.getCustomerOrder().getCustomerOrderNo());
        return deliverOrder;
    }

    @Override
    public void startDeliver(String deliverOrderNo) {
        DeliverOrder deliverOrder = deliverOrderRepository.findByDeliverOrderNo(deliverOrderNo);
        if (deliverOrder == null) {
            throw new BizException(Results.ErrorCode.ORDER_NOT_EXIST);
        }
        deliverOrder.setDeliverOrderState(Constant.OrderState.ONBOARD);
        deliverOrder.getCustomerOrder().setState(Constant.OrderState.ONBOARD);
        deliverOrder.preUpdate();
        deliverOrderRepository.save(deliverOrder);
    }

    @Override
    public void confirmDeliver(String deliverOrderNo) {
        DeliverOrder deliverOrder = deliverOrderRepository.findByDeliverOrderNo(deliverOrderNo);
        if (deliverOrder == null) {
            throw new BizException(Results.ErrorCode.ORDER_NOT_EXIST);
        }
        deliverOrder.setDeliverOrderState(Constant.OrderState.CONFIRMED);
        deliverOrder.getCustomerOrder().setState(Constant.OrderState.CONFIRMED);
        deliverOrder.preUpdate();
        deliverOrderRepository.save(deliverOrder);
    }

    @Override
    public void split(SplitDto splitDto) {
        DeliverOrder originalOrder = deliverOrderRepository.findByDeliverOrderNo(splitDto.getId());
        splitDto.getSplitCubes().forEach(splitTuple -> {
            DeliverOrder deliverOrder = new DeliverOrder();
            BeanUtils.copyProperties(originalOrder, deliverOrder, "id", "deliverOrderNo", "cargoes");
            List<DeliverCargo> deliveryCargoes = originalOrder.getCargoes().stream().map(cargo -> {
                DeliverCargo cargo1 = new DeliverCargo();
                BeanUtils.copyProperties(cargo, cargo1, "id", "deliverOrder");
                cargo1.setWeight(splitTuple.getWeight());
                cargo1.setVolume(splitTuple.getVolume());
                cargo1.setDeliverOrder(deliverOrder);
                return cargo1;
            }).collect(Collectors.toList());
            deliverOrder.setCargoes(deliveryCargoes);

            deliverOrder.setDeliverPrice(splitTuple.getDeliverPrice());
            deliverOrder.setDeliverOrderNo(deliverOrder.genOrderNo());
            deliverOrder.setDeliverOrderState(Constant.OrderState.NOT_DISTRIBUTED);
            deliverOrder.setParentNo(originalOrder.getDeliverOrderNo());
            deliverOrder.preInsert();
            deliverOrderRepository.save(deliverOrder);
        });
        originalOrder.setDeliverOrderState(Constant.OrderState.SPLITTED);
        deliverOrderRepository.save(originalOrder);

    }


    @Override
    public void completeDeliver(String deliverOrderNo) {
        DeliverOrder deliverOrder = deliverOrderRepository.findByDeliverOrderNo(deliverOrderNo);
        if (deliverOrder == null) {
            throw new BizException(Results.ErrorCode.ORDER_NOT_EXIST);
        }
        deliverOrder.setDeliverOrderState(deliverOrder.getCustomerOrder().getPayment().getPayType().equals(Constant.PayType.SENDER_PAY) ? Constant.OrderState.COMPLETED : Constant.OrderState.NOT_PAID);
        deliverOrder.getCustomerOrder().setState(Constant.OrderState.COMPLETED);
        deliverOrder.setArriveTime(new Date());
        deliverOrder.preUpdate();
        //运单结束后恢复车辆载重
        Float addLoads = deliverOrder.getCargoes().get(0).getWeight().floatValue();
        Float remainLoads= deliverOrder.getVehicle().getRemainLoads().floatValue() + addLoads;
        if(remainLoads.compareTo(deliverOrder.getVehicle().getLoads())>0)
            remainLoads = deliverOrder.getVehicle().getLoads();
        deliverOrder.getVehicle().setRemainLoads(remainLoads);

        deliverOrderRepository.save(deliverOrder);
        boolean isComplete = true;



        List<DeliverOrder> deliverOrders = deliverOrder.getCustomerOrder().getDeliverOrders();
        for (DeliverOrder deliverOrder1 : deliverOrders) {
            if (deliverOrder1.getDeliverOrderState() != Constant.OrderState.COMPLETED) {
                isComplete = false;
                break;
            }
        }
        if (isComplete) {
            customerOrderService.completeCustomerOrderDetail(deliverOrder.getCustomerOrder().getCustomerOrderNo());
        }
        //需要根据订单的体积重量修改汽车的

    }

    @Override
    public Page<DeliverOrderResponseVo> queryDeliverOrder(QueryDeliverOrderRequestVo queryDeliverOrderRequestVo, Pageable page) {

        Page domainPage = deliverOrderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (!StringUtils.isEmpty(queryDeliverOrderRequestVo.getId())) {
                predicate.add(criteriaBuilder.equal(root.get("deliverOrderNo"), queryDeliverOrderRequestVo.getId()));
            }
            if (queryDeliverOrderRequestVo.getFrom() != null) {
                if (!StringUtils.isEmpty(queryDeliverOrderRequestVo.getFrom().getName())) {
                    predicate.add(criteriaBuilder.like(root.join("from").get("name"), "%" + queryDeliverOrderRequestVo.getFrom().getName() + "%"));
                }
                if (!StringUtils.isEmpty(queryDeliverOrderRequestVo.getFrom().getPhone())) {
                    predicate.add(criteriaBuilder.equal(root.join("from").get("phone"), queryDeliverOrderRequestVo.getFrom().getPhone()));
                }
                if (!StringUtils.isEmpty(queryDeliverOrderRequestVo.getFrom().getAddress())) {
                    predicate.add(criteriaBuilder.like(root.join("from").get("address"), "%" + queryDeliverOrderRequestVo.getFrom().getAddress() + "%"));
                }
            }
            if (queryDeliverOrderRequestVo.getTo() != null) {
                if (!StringUtils.isEmpty(queryDeliverOrderRequestVo.getTo().getName())) {
                    predicate.add(criteriaBuilder.like(root.join("to").get("name"), "%" + queryDeliverOrderRequestVo.getTo().getName() + "%"));
                }
                if (!StringUtils.isEmpty(queryDeliverOrderRequestVo.getTo().getPhone())) {
                    predicate.add(criteriaBuilder.equal(root.join("to").get("phone"), queryDeliverOrderRequestVo.getTo().getPhone()));
                }
                if (!StringUtils.isEmpty(queryDeliverOrderRequestVo.getTo().getAddress())) {
                    predicate.add(criteriaBuilder.like(root.join("to").get("address"), "%" + queryDeliverOrderRequestVo.getTo().getAddress() + "%"));
                }
            }
            if (queryDeliverOrderRequestVo.getStartTime() != null) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), queryDeliverOrderRequestVo.getStartTime()));
            }
            if (queryDeliverOrderRequestVo.getEndTime() != null) {
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), queryDeliverOrderRequestVo.getEndTime()));
            }
            if (queryDeliverOrderRequestVo.getDeliverOrderState() != null) {
                predicate.add(criteriaBuilder.equal(root.get("deliverOrderState").as(Constant.OrderState.class), queryDeliverOrderRequestVo.getDeliverOrderState()));
            }
            return criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        }, page);

        Page voPage = domainPage.map((Converter<DeliverOrder, DeliverOrderResponseVo>) deliverOrder -> new DeliverOrderResponseVo(deliverOrder));
        return voPage;
    }

    @Override
    public DeliverOrder queryDeliverOrderByNo(String number) {
        return deliverOrderRepository.findByDeliverOrderNo(number);
    }

}
