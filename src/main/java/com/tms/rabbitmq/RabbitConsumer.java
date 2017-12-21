package com.tms.rabbitmq;

import com.tms.config.RabbitMqConfig;
import com.tms.model.DeliverOrder;
import com.tms.model.Driver;
import com.tms.model.Message;
import com.tms.repository.DeliverOrderRepository;
import com.tms.repository.DriverRepository;
import com.tms.repository.MessageRepository;
import com.tms.service.DeliverOrderService;
import com.tms.service.RouteService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitConsumer {
    @Autowired
    private RouteService routeService;
    @Autowired
    private DeliverOrderRepository deliverOrderRepository;
//    @Autowired
//    private SysUserRepository sysUserRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DeliverOrderService deliverOrderService;

    @RabbitHandler
    @RabbitListener(queues = RabbitMqConfig.DRIVER_ORDER_QUEUE)
    public void driverReceive(DeliverOrder deliverOrder) {
        Message message = new Message();
        message.setDeliverOrder(deliverOrder);
        message.setMsgType(Message.MsgType.NEW_ORDER);
        message.setRead(false);
        message.setMsgAuth(Message.MsgAuth.DRIVER);
        Iterable<Driver> drivers = driverRepository.findAll();
        drivers.forEach(driver -> {
            message.setDriver(driver);
            messageRepository.save(message);
        });
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqConfig.MANAGER_ORDER_QUEUE)
    public void managerReceive(DeliverOrder deliverOrder) {
//        Message message = new Message();
//        message.setDeliverOrder(deliverOrder);
//        message.setMsgType(Message.MsgType.NEW_ORDER);
//        message.setRead(false);
//        message.setMsgAuth(Message.MsgAuth.MANAGER);
//        Iterable<SysUser> sysUsers = sysUserRepository.findAll();
//        sysUsers.forEach(user -> {
//            message.setSysUser(user);
//            messageRepository.save(message);
//        });
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqConfig.ROUTER_ORDER_QUEUE)
    public void routerReceive(DeliverOrder deliverOrder) {
        Map<String, Long> result = routeService.autoAllocate(deliverOrder);
        if (result != null && result.size() > 0) {
            deliverOrderService.allocateVoyageAndDriver(deliverOrder.getDeliverOrderNo(), result.get("voyageId"), result.get("driverId"));
        }
        //TODO 如果自动分配没有结果，那么该订单该怎样？
    }
}
