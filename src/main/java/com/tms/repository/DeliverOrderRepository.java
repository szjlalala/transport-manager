package com.tms.repository;

import com.tms.model.CustomerOrderDetail;
import com.tms.model.DeliverOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverOrderRepository extends CrudRepository<DeliverOrder, Long>, JpaSpecificationExecutor {
    List<DeliverOrder> findByCustomerOrderDetailAndDeliverOrderStateOrderBySequenceAsc(CustomerOrderDetail customerOrderDetail, DeliverOrder.DeliverOrderState deliverOrderState);

    DeliverOrder findByDeliverOrderNo(String deliverOrderNo);
}
