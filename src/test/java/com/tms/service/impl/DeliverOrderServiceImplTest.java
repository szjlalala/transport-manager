package com.tms.service.impl;

import com.tms.TransportManagerApplication;
import com.tms.service.DeliverOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TransportManagerApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeliverOrderServiceImplTest {
    @Autowired
    private DeliverOrderService deliverOrderService;
    @Test
    public void spreadDeliverOrder() {
    }

    @Test
    public void allocateVoyageAndDriver() {
        deliverOrderService.allocateVoyageAndDriver("D31607614406660",1l,1l);
    }

    @Test
    public void startDeliver() {
        deliverOrderService.startDeliver("D31607614406660");
    }

    @Test
    public void completeDeliver() {
        deliverOrderService.completeDeliver("D31607614406660");
    }
}