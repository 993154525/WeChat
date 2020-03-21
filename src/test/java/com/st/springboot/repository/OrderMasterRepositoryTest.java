package com.st.springboot.repository;

import com.st.springboot.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author shaotian
 * @create 2020-02-18 21:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("shao9881");
        orderMaster.setBuyerName("st2");
        orderMaster.setBuyerPhone("18874470670");
        orderMaster.setBuyerAddress("杨市镇");
        orderMaster.setBuyerOpenid("weixin");
        orderMaster.setOrderAmount(new BigDecimal(3));
        repository.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        /** 前面数字是第几页，后面是每页几个  */
        PageRequest pageRequest = PageRequest.of(0, 1);

        Page<OrderMaster> orderMasters = repository.findByBuyerOpenid("158220678954368315", pageRequest);
        for (OrderMaster orderMaster : orderMasters) {
            System.out.println(orderMaster.getBuyerOpenid());
        }
    }

    @Test
    public void findByBuyerNameLike(){
        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<OrderMaster> orderMasters = repository.findByBuyerNameLike("188744", pageRequest);

        for (OrderMaster orderMaster : orderMasters) {
            System.out.println(orderMaster.getBuyerOpenid());
        }
    }

}