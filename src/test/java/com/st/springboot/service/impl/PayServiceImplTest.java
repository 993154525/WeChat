package com.st.springboot.service.impl;

import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.service.OrderService;
import com.st.springboot.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author shaotian
 * @create 2020-03-12 14:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayServiceImpl payService;

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderMasterDto orderMasterDto = orderService.findOne("158220678940975158");
        payService.create(orderMasterDto);
    }

    @Test
    public void notif() {
    }

    @Test
    public void refund() {
    }
}