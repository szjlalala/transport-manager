package com.tms.service.impl;


import com.tms.config.RabbitMqConfig;
import com.tms.model.DeliverOrder;
import com.tms.rabbitmq.DefaultRabbitProducerSupport;
import com.tms.service.MQProducer;
import org.springframework.stereotype.Service;

@Service
public class MQProducerImpl extends DefaultRabbitProducerSupport implements MQProducer {

    @Override
    public void sendDeliverOrderToDriver(DeliverOrder deliverOrder) {
        send(RabbitMqConfig.DRIVER_ORDER_QUEUE, deliverOrder);
    }

    @Override
    public void sendDeliverOrderToManager(DeliverOrder deliverOrder) {
        send(RabbitMqConfig.MANAGER_ORDER_QUEUE, deliverOrder);
    }

    @Override
    public void sendDeliverOrderToRouter(DeliverOrder deliverOrder) {
        send(RabbitMqConfig.ROUTER_ORDER_QUEUE, deliverOrder);
    }
}
