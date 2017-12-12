package com.tms.repository;

import com.tms.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
}
