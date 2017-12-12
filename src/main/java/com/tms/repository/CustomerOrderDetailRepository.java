package com.tms.repository;

import com.tms.model.CustomerOrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderDetailRepository extends PagingAndSortingRepository<CustomerOrderDetail, Long> {
    CustomerOrderDetail findByOrOrderDetailNo(String orderDetailNo);
}
