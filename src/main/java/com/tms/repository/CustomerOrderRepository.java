package com.tms.repository;

import com.tms.model.CustomerOrder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends PagingAndSortingRepository<CustomerOrder, Long> {
}
