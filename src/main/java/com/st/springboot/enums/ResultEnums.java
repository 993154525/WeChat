package com.st.springboot.enums;

import lombok.Getter;

/**
 * @author shaotian
 * @create 2020-02-20 19:53
 */
@Getter
public enum ResultEnums {

    PARAM_ORROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(20, "货品库存不足"),
    ORDER_MASTER_ERROR(30, "订单不存在"),
    ORDER_DETAIL_ERROR(40, "订单详情不存在"),
    ORDER_STATUS_ERROR(50, "订单状态不正确"),
    CART_EMPTY(60, "购物车不能为空"),
    WX_ORDER_OPPEN(70, "微信ID找不到"),
    ORDER_ERROR(80, "订单取消成功"),
    ORDER_FINISH(90, "订单完结成功"),
    PRODUCT_NOT_ERROR(100, "商品不存在"),
    PRODUCT_Status_ERROR(110, "商品状态错误");


    private Integer code;

    private String mssage;

    ResultEnums(Integer code, String mssage) {
        this.code = code;
        this.mssage = mssage;
    }
}
