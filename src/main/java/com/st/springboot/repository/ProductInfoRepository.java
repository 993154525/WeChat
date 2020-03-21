package com.st.springboot.repository;

import com.st.springboot.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-16 21:23
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo findByProductPrice(BigDecimal productPrice);
}
