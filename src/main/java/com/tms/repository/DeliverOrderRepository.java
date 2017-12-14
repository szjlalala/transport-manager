package com.tms.repository;

import com.tms.model.CustomerOrderDetail;
import com.tms.model.DeliverOrder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverOrderRepository extends PagingAndSortingRepository<DeliverOrder, Long> {
    List<DeliverOrder> findByCustomerOrderDetailAndDeliverOrderStateOrderBySequenceAsc(CustomerOrderDetail customerOrderDetail, DeliverOrder.DeliverOrderState deliverOrderState);

    DeliverOrder findByDeliverOrderNo(String deliverOrderNo);
}
