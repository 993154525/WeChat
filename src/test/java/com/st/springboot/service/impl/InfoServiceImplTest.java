package com.st.springboot.service.impl;

import com.st.springboot.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 商品测试
 *
 * @author shaotian
 * @create 2020-02-16 22:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoServiceImplTest {

    @Autowired
    private InfoServiceImpl infoService;

    @Test
    public void findOne() {
        ProductInfo one = infoService.findOne("7747");
        System.out.println(one.toString());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = infoService.findUpAll();
        for (ProductInfo productInfo : upAll) {
            System.out.println(productInfo.toString());
        }
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> infoServiceAll = infoService.findAll(pageRequest);
        for (ProductInfo productInfo : infoServiceAll) {
            System.out.println(productInfo.toString());
        }
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("85489");
        productInfo.setProductName("可乐");
        productInfo.setProductPrice(new BigDecimal(4));
        productInfo.setProductStock(1000);
        productInfo.setProductDescription("好喝的鸡可乐");
        productInfo.setProductIcon("http://xxxx");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(10);

        infoService.save(productInfo);
    }

    @Test
    public void onsale() {
        String productIdid = "7747";
        infoService.onsale(productIdid);
    }

    @Test
    public void offsale() {
        String productIdid = "7747";
        infoService.offSale(productIdid);
    }
}