package com.st.springboot.repository;

import com.st.springboot.dataobject.ProductInfo;
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
 * @create 2020-02-16 21:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository infoRepository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("782139");
        productInfo.setProductName("可乐");
        productInfo.setProductPrice(new BigDecimal(4));
        productInfo.setProductStock(1000);
        productInfo.setProductDescription("好喝的鸡可乐");
        productInfo.setProductIcon("http://xxxx");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(10);
        infoRepository.save(productInfo);
    }

    @Test
    public void findByProductStatus() {

        ProductInfo info = infoRepository.findById("7747").get();
        System.out.println(info.toString());

//        List<ProductInfo> productInfos = infoRepository.findByProductStatus(0);
//
//        for (ProductInfo productInfo : productInfos) {
//            System.out.println(productInfo.toString());
//        }
    }

    @Test
    public void findByProductPrice() {
        ProductInfo productInfos = infoRepository.findByProductPrice(new BigDecimal(4));
        System.out.println(productInfos.toString());

    }

}