package com.st.springboot.service.impl;

import com.st.springboot.dataobject.OrderDetail;
import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.enums.OrderMasterEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author shaotian
 * @create 2020-02-20 21:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private static String Openid = "158220678954368315";

    private String OrderId = "158225459140738047";

    @Test
    @Transactional
    public void create() {

        OrderMasterDto orderMasterDto = new OrderMasterDto();

        orderMasterDto.setBuyerAddress("湖南娄底");
        orderMasterDto.setBuyerName("shaotian");
        orderMasterDto.setBuyerPhone("18874470670");
        orderMasterDto.setBuyerOpenid(Openid);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("7747");
        orderDetail.setProductQuantity(2);
        orderDetailList.add(orderDetail);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("85489");
        orderDetail2.setProductQuantity(2);
        orderDetailList.add(orderDetail2);

        orderMasterDto.setOrderDetailList(orderDetailList);
        orderService.create(orderMasterDto);

    }

    @Test
    @Transactional
    public void findOne() {
        OrderMasterDto dto = orderService.findOne(OrderId);

        System.out.println(dto.toString());
    }

    @Test
    @Transactional
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 2);

        Page<OrderMasterDto> serviceList = orderService.findList(Openid, pageRequest);

        for (OrderMasterDto orderMasterDto : serviceList) {
            System.out.println(orderMasterDto.toString());
        }
    }

    @Test
    @Transactional
    public void cancel() {
        OrderMasterDto orderMasterDto = orderService.findOne(OrderId);
        orderService.cancel(orderMasterDto);

    }

    @Test
    @Transactional
    public void finish() {
        OrderMasterDto orderMasterDto = orderService.findOne(OrderId);
        OrderMasterDto result = orderService.finish(orderMasterDto);
        Assert.assertEquals(OrderMasterEnums.FiNSH.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderMasterDto orderMasterDto = orderService.findOne(OrderId);
        orderService.paid(orderMasterDto);
    }

    @Test
    public void findList2() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderMasterDto> serviceList = orderService.findList(pageRequest);
//        Assert.assertNotEquals(0, serviceList);
        Assert.assertTrue("查询所有的订单列表", serviceList.getTotalElements() < 0);
    }
}