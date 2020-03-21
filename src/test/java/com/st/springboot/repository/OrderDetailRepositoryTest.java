package com.st.springboot.repository;

import com.st.springboot.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author shaotian
 * @create 2020-02-18 21:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("s8");
        orderDetail.setOrderId("shao98");
        orderDetail.setProductId("7799");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductName("梦龙");
        orderDetail.setProductPrice(new BigDecimal(11));
        orderDetail.setProductQuantity(1);

        repository.save(orderDetail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("shao98");

        for (OrderDetail orderDetail : orderDetailList) {
            System.out.println(orderDetail.toString());
        }

    }
}