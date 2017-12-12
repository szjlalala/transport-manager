package com.tms.service;


import com.tms.model.DeliverOrder;

public interface MQProducer {
    void sendDeliverOrderToDriver(DeliverOrder deliverOrder);
    void sendDeliverOrderToManager(DeliverOrder deliverOrder);
    void sendDeliverOrderToRouter(DeliverOrder deliverOrder);
}
