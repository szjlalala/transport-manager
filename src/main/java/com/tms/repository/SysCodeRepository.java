package com.tms.repository;

import com.tms.model.SysCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysCodeRepository extends CrudRepository<SysCode, Long> {
    SysCode findByCode(String code);
}
