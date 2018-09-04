package com.tms.repository;

import com.tms.model.Vehicle;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long>, JpaSpecificationExecutor {

    Vehicle findByPlateNumber(String plateNumber);
}
