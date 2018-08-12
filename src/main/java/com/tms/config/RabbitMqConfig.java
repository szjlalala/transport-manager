package com.tms.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;

@Configuration
public class RabbitMqConfig {
    public final static String EXCHANGE = "tms-exchange";

    public final static String DRIVER_ORDER_QUEUE = "driver_order_queue";
    public final static String MANAGER_ORDER_QUEUE = "manager_order_queue";
    public final static String ROUTER_ORDER_QUEUE = "router_order_queue";


    @Bean("driverOrderQueue")
    Queue driverOrderQueue() {
        return new Queue(DRIVER_ORDER_QUEUE);
    }

    @Bean("managerOrderQueue")
    Queue managerOrderQueue() {
        return new Queue(MANAGER_ORDER_QUEUE);
    }

    @Bean("routerOrderQueue")
    Queue routerOrderQueue() {
        return new Queue(ROUTER_ORDER_QUEUE);
    }

    @Bean("tmsExchange")
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding bindingDriver(Queue driverOrderQueue, DirectExchange tmsExchange) {
//        BlockingQueue
        return BindingBuilder.bind(driverOrderQueue).to(tmsExchange).with(DRIVER_ORDER_QUEUE);
    }

    @Bean
    Binding bindingManager(Queue managerOrderQueue, DirectExchange tmsExchange) {
        return BindingBuilder.bind(managerOrderQueue).to(tmsExchange).with(MANAGER_ORDER_QUEUE);
    }

    @Bean
    Binding bindingRouter(Queue routerOrderQueue, DirectExchange tmsExchange) {
        return BindingBuilder.bind(routerOrderQueue).to(tmsExchange).with(ROUTER_ORDER_QUEUE);
    }
}
