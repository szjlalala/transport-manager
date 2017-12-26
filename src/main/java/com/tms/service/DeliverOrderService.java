package com.tms.service;


import com.tms.model.CustomerOrderDetail;
import com.tms.model.DeliverOrder;

import java.util.List;

public interface DeliverOrderService {
    List<DeliverOrder> createDeliverOrder(CustomerOrderDetail customerOrderDetail);

    void spreadDeliverOrder(DeliverOrder deliverOrder);

    DeliverOrder allocateVehicleAndDriver(String deliverOrderNo, Long voyageId, Long driverId);

    void startDeliver(String deliverOrderNo);

    void completeDeliver(String deliverOrderNo);

}
