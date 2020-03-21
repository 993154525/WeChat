package com.st.springboot.enums;

import lombok.Getter;

/**
 * 商品列表
 *
 * @author shaotian
 * @create 2020-02-16 22:21
 */
@Getter
public enum ProductInfoEnums{
    UP(0, "在架"),
    Down(1, "下架");

    private int code;

    private String message;

    ProductInfoEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
