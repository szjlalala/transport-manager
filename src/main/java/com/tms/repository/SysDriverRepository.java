package com.tms.repository;

import com.tms.model.Driver;
import com.tms.model.SysDriver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDriverRepository extends CrudRepository<SysDriver, Long> {
    SysDriver findByDriver(Driver driver);
}
