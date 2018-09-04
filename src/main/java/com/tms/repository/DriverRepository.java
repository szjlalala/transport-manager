package com.tms.repository;

import com.tms.common.Constant;
import com.tms.model.BaseModel;
import com.tms.model.CustomerOrder;
import com.tms.model.DeliverOrder;
import com.tms.model.Driver;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends CrudRepository<Driver, String>, JpaSpecificationExecutor, DriverRepositoryInterface<Driver>{
    @Override
    Driver findByCreator(long id);
}
