package com.tms.repository;

import com.tms.common.Constant;
import com.tms.model.CustomerOrder;
import com.tms.model.DeliverOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverOrderRepository extends CrudRepository<DeliverOrder, Long>, JpaSpecificationExecutor {
    List<DeliverOrder> findByCustomerOrderAndDeliverOrderStateOrderBySequenceAsc(CustomerOrder customerOrder, Constant.DeliverOrderState deliverOrderState);

    DeliverOrder findByDeliverOrderNo(String deliverOrderNo);
}
