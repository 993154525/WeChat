package com.st.springboot.repository;

import com.st.springboot.dataobject.SellerInfo;
import com.st.springboot.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author shaotian
 * @create 2020-03-19 22:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genKeyUtil());
        sellerInfo.setUserName("admin");
        sellerInfo.setPassWord("admin");
        sellerInfo.setOpenId("shao");

        sellerInfoRepository.save(sellerInfo);
    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenId("shao");
        Assert.assertEquals("shao", sellerInfo.getOpenId());
    }
}