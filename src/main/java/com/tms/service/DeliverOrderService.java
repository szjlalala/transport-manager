package com.tms.service;


import com.tms.controller.vo.request.QueryDeliverOrderRequestVo;
import com.tms.controller.vo.request.SplitDto;
import com.tms.model.CustomerOrder;
import com.tms.model.DeliverOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeliverOrderService {
    List<DeliverOrder> createDeliverOrder(CustomerOrder customerOrder);

    void spreadDeliverOrder(DeliverOrder deliverOrder);

    DeliverOrder allocateVehicleAndDriver(String deliverOrderNo, Long voyageId, String driverId);

    void startDeliver(String deliverOrderNo);

    void completeDeliver(String deliverOrderNo);

    Page queryDeliverOrder(QueryDeliverOrderRequestVo queryDeliverOrderRequestVo, Pageable page);

    DeliverOrder queryDeliverOrderByNo(String number);

    void confirmDeliver(String deliverOrderNo);

    void split(SplitDto splitDto);
}
