package com.tms.repository;

import com.tms.model.CustomerOrder;
import com.tms.model.Payment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends PagingAndSortingRepository<CustomerOrder, Long>, JpaSpecificationExecutor {
    CustomerOrder findByCustomerOrderNo(String customerOrderNo);

    List<CustomerOrder> findByPayment(Payment payment);
}
