package com.tms.service.impl;


import com.tms.model.CustomerOrder;
import com.tms.model.DeliverOrder;
import com.tms.repository.SysCodeRepository;
import com.tms.service.MQProducer;
import com.tms.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private SysCodeRepository sysCodeRepository;
    @Autowired
    private MQProducer mqProducer;

    @Override
    public List<DeliverOrder> designRoute(CustomerOrder customerOrder) {
        List<DeliverOrder> deliverOrders = new ArrayList<>();
        switch (customerOrder.getDeliverType()) {
            case SAME_CITY:
                DeliverOrder deliverOrder = new DeliverOrder(customerOrder, 0);
                deliverOrders.add(deliverOrder);
                break;
            case NATIONAL://TODO 生成中转运单列表
                break;
        }
        return deliverOrders;
    }

    @Override
    public Map<String,Object> autoAllocate(DeliverOrder deliverOrder) {

        //TODO 自动分配车和司机
        return null;
    }
}
