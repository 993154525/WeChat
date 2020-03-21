package com.st.springboot.service;

import com.st.springboot.dataobject.ProductInfo;
import com.st.springboot.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品类
 *
 * @author shaotian
 * @create 2020-02-16 22:06
 */
public interface InfoService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDto> cartDtoList);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDto> cartDtoList);

    /**
     * 上架
     */
    ProductInfo onsale(String productId);

    /**
     * 下架
     */
    ProductInfo offSale(String productId);

}
