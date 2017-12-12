package com.tms.service.impl;

import com.tms.model.*;
import com.tms.repository.DeliverOrderRepository;
import com.tms.repository.DriverRepository;
import com.tms.repository.SysCodeRepository;
import com.tms.repository.VoyageRepository;
import com.tms.service.DeliverOrderService;
import com.tms.service.MQProducer;
import com.tms.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private VoyageRepository voyageRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private MQProducer mqProducer;

    @Override
    public List<DeliverOrder> createDeliverOrder(CustomerOrderDetail customerOrderDetail) {
        List<DeliverOrder> deliverOrders = routeService.designRoute(customerOrderDetail);
        for (DeliverOrder deliverOrder : deliverOrders) {
            deliverOrderRepository.save(deliverOrder);
        }
        return deliverOrders;
    }

    @Override
    public void spreadDeliverOrder(DeliverOrder deliverOrder) {
        //根据当前调度模式进行派单
        SysCode sysCode = sysCodeRepository.findByCode("allocate_type");
        SysCode.AllocateType allocateType = SysCode.AllocateType.values()[Integer.parseInt(sysCode.getaValue())];
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
    public DeliverOrder allocateVoyageAndDriver(Long deliverId, Long voyageId, Long driverId) {
        DeliverOrder deliverOrder = deliverOrderRepository.findOne(deliverId);
        Driver driver = driverRepository.findOne(driverId);
        Voyage voyage = voyageRepository.findOne(voyageId);
        deliverOrder.setDriver(driver);
        deliverOrder.setVoyage(voyage);
        deliverOrder.setDeliverOrderState(DeliverOrder.DeliverOrderState.LOADED);
        return deliverOrderRepository.save(deliverOrder);
    }


}
