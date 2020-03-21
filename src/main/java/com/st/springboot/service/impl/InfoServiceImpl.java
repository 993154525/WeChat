package com.st.springboot.service.impl;

import com.st.springboot.dataobject.ProductInfo;
import com.st.springboot.dto.CartDto;
import com.st.springboot.enums.ProductInfoEnums;
import com.st.springboot.enums.ProductStatusEnum;
import com.st.springboot.enums.ResultEnums;
import com.st.springboot.exception.SellException;
import com.st.springboot.repository.ProductInfoRepository;
import com.st.springboot.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-16 22:12
 */
@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {

        return repository.findByProductStatus(ProductInfoEnums.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = repository.findById(cartDto.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnums.ORDER_MASTER_ERROR);
            }
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = repository.findById(cartDto.getProductId()).get();

            Integer sum = productInfo.getProductStock() - cartDto.getProductQuantity();

            if (sum < 0) {
                throw new SellException(ResultEnums.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(sum);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onsale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productId == null) {
            throw new SellException(ResultEnums.PRODUCT_NOT_ERROR);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnums.PRODUCT_Status_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productId == null) {
            throw new SellException(ResultEnums.PRODUCT_NOT_ERROR);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnums.PRODUCT_Status_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }


}
