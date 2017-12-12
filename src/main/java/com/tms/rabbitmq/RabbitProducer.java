package com.tms.rabbitmq;

public interface RabbitProducer {
    void send(String routingKey, Object message);
}
