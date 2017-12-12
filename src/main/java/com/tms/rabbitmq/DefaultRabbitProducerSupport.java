package com.tms.rabbitmq;

import com.tms.config.RabbitMqConfig;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class DefaultRabbitProducerSupport implements RabbitProducer {
    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(String routingKey, Object message) {
        this.amqpTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, routingKey, message);
    }
}
