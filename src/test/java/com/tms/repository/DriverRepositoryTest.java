package com.tms.repository;

import com.tms.TransportManagerApplication;
import com.tms.model.BaseModel;
import com.tms.model.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by song on 2018/9/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TransportManagerApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverRepositoryTest {
    @Autowired
    DriverRepository driverRepository;

    @Test
    public void findByCreator() throws Exception {

        Driver d2 = (Driver) driverRepository.findByCreator(1);
        Driver driver;
        System.out.println(d2.getName());
    }
}