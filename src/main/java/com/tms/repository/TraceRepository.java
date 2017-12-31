package com.tms.repository;

import com.tms.model.Trace;
import com.tms.model.Vehicle;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceRepository extends CrudRepository<Trace, Long>, JpaSpecificationExecutor {
}
