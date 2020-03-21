package com.st.springboot.VO;

import lombok.Data;

/**
 * @author shaotian
 * @create 2020-02-17 10:07
 */
@Data
public class ResultVO<T> {

    /** 错误码 **/
    private Integer code;

    /** 错误信息 **/
    private String msg;

    /** 商品详情  **/
    private T data;

}
