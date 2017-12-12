package com.tms.service;


import com.tms.model.CustomerOrderDetail;
import com.tms.model.DeliverOrder;

import java.util.List;
import java.util.Map;

public interface RouteService {
    List<DeliverOrder> designRoute(CustomerOrderDetail customerOrderDetail);

    Map<String, Long> autoAllocate(DeliverOrder deliverOrder);
}
