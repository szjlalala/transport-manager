package com.tms.service.impl;


import com.tms.TransportManagerApplication;
import com.tms.controller.vo.CustomerOrderVo;
import com.tms.model.*;
import com.tms.service.CustomerOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TransportManagerApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerOrderServiceImplTest {
    @Autowired
    CustomerOrderService customerOrderService;

    @Test
    public void createCustomerOrder() {
        CustomerOrderVo customerOrderVo = new CustomerOrderVo();
        customerOrderVo.setPayType(Payment.PayType.RECEIVER_PAY);
        customerOrderVo.setCustomerRemark("123");
        customerOrderVo.setSource(CustomerOrder.OrderSource.BACK);
        List<CustomerOrderDetail> customerOrderDetails = new ArrayList<>();
        CustomerOrderDetail customerOrderDetail1 = new CustomerOrderDetail();
        CustomerOrderDetail customerOrderDetail2 = new CustomerOrderDetail();
        Location from = new Location();
        from.setLocationType(Location.LocationType.CUSTOMER);
        from.setName("www");
        from.setAddress("sss");
        from.setCityCode(1l);
        from.setDistrictCode(2l);
        from.setProvinceCode(3l);
        from.setPhone("18518491538");
        from.setX("123");
        from.setY("456");
        List<Cargo> cargoes = new ArrayList<>();
        Cargo cargo = new Cargo();
        cargo.setCargoType(Cargo.CargoType.NORMAL);
        cargo.setCount(1l);
        cargo.setName("www");
        cargo.setPrice(new BigDecimal("10"));
        cargo.setRemark("123");
        cargo.setVolume(new BigDecimal("30"));
        cargo.setWeight(new BigDecimal("20"));
        cargoes.add(cargo);
        cargoes.add(cargo);
        customerOrderDetail1.setFrom(from);
        customerOrderDetail1.setTo(from);
        customerOrderDetail1.setCargoes(cargoes);
        customerOrderDetail2.setFrom(from);
        customerOrderDetail2.setTo(from);
        customerOrderDetail2.setCargoes(cargoes);
        customerOrderDetails.add(customerOrderDetail1);
        customerOrderDetails.add(customerOrderDetail2);
        customerOrderVo.setOrderDetails(customerOrderDetails);
        customerOrderService.createCustomerOrder(customerOrderVo);
    }
}