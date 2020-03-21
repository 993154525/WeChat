package com.st.springboot.service.impl;

import com.st.springboot.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author shaotian
 * @create 2020-03-20 10:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findbySellerOpenid() {
        SellerInfo sellerInfo = sellerService.findbySellerOpenid("shao");
        Assert.assertEquals("shao", sellerInfo.getOpenId());
    }
}