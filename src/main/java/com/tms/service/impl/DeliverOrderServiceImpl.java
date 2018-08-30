package com.tms.service.impl;

import com.tms.common.BizException;
import com.tms.common.Constant;
import com.tms.common.Results;
import com.tms.controller.vo.request.QueryDeliverOrderRequestVo;
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
        deliverOrder.setDriver(driver);
        deliverOrder.setVehicle(vehicle);
        deliverOrder.preUpdate();
        deliverOrder.setDeliverOrderState(Constant.OrderState.NOT_RECEIVED);
        deliverOrder.setStartTime(new Date());
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
        deliverOrder.preUpdate();
        deliverOrderRepository.save(deliverOrder);
    }


    @Override
    public void completeDeliver(String deliverOrderNo) {
        DeliverOrder deliverOrder = deliverOrderRepository.findByDeliverOrderNo(deliverOrderNo);
        if (deliverOrder == null) {
            throw new BizException(Results.ErrorCode.ORDER_NOT_EXIST);
        }
        deliverOrder.setDeliverOrderState(deliverOrder.getCustomerOrder().getPayment().getPayType().equals(Constant.PayType.SENDER_PAY) ? Constant.OrderState.COMPLETED : Constant.OrderState.NOT_PAID);
        deliverOrder.setEndTime(new Date());
        deliverOrder.preUpdate();
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
