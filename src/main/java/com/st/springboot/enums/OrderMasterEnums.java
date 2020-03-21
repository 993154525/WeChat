package com.st.springboot.enums;

import lombok.Getter;

/**
 * @author shaotian
 * @create 2020-02-18 20:55
 */
@Getter
public enum OrderMasterEnums implements CodeEnum{

    New(0, "新订单"),
    FiNSH(1, "已完成"),
    CANCEL(2, "已取消");
    private Integer code;

    private String mssage;

    OrderMasterEnums(Integer code, String mssage) {
        this.code = code;
        this.mssage = mssage;
    }

}
