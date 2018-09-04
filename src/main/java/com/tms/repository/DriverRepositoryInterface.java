package com.tms.repository;

import com.tms.model.BaseModel;

/**
 * Created by song on 2018/9/3.
 */
public interface DriverRepositoryInterface<T extends BaseModel> {
    T findByCreator(long id);
}
