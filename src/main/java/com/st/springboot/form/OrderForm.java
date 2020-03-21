package com.st.springboot.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author shaotian
 * @create 2020-03-06 20:31
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 手机号码
     */
    @NotEmpty(message = "手机必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "address")
    private String address;

    /**
     * 买家openid
     */
    @NotEmpty(message = "openid")
    private String openid;

    /**
     *  购买商品详情
     */
    @NotEmpty(message = "items")
    private String items;

}
