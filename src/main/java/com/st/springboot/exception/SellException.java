package com.st.springboot.exception;

import com.st.springboot.enums.ResultEnums;

/**
 * @author shaotian
 * @create 2020-02-20 19:55
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnums enums) {
        super(enums.getMssage());

        this.code = enums.getCode();
    }

    public SellException(Integer code, String message) {

        super(message);
        this.code = code;
    }
}
