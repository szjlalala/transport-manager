package com.tms.service;


import com.tms.model.CustomerOrder;
import com.tms.model.DeliverOrder;

import java.util.List;
import java.util.Map;

public interface RouteService {
    List<DeliverOrder> designRoute(CustomerOrder customerOrder);

    Map<String, Object> autoAllocate(DeliverOrder deliverOrder);
}
