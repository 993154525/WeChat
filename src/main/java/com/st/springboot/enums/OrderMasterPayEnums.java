package com.st.springboot.enums;

import lombok.Getter;

/**
 * @author shaotian
 * @create 2020-02-18 21:00
 */
@Getter
public enum OrderMasterPayEnums implements CodeEnum{

    PayWait(0, "等待支付"),
    PaySuccess(1, "支付成功")
    ;
    private Integer code;

    private String message;

    OrderMasterPayEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



}
