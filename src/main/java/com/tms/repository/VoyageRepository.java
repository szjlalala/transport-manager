package com.tms.repository;

import com.tms.model.Voyage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyageRepository extends CrudRepository<Voyage, Long> {
}
