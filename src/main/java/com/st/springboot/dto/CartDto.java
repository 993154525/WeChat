package com.st.springboot.dto;

import lombok.Data;

/**
 * @author shaotian
 * @create 2020-02-20 21:11
 */
@Data
public class CartDto {

    private String productId;

    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
