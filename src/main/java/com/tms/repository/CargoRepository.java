package com.tms.repository;

import com.tms.model.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {
}
