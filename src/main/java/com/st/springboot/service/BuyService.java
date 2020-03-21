package com.st.springboot.service;

import com.st.springboot.dto.OrderMasterDto;

/**
 * @author shaotian
 * @create 2020-03-09 21:25
 */
public interface BuyService {

    /**
     * 查询订单并验证
     * @param openId
     * @param orderId
     * @return
     */
    OrderMasterDto findone(String openId, String orderId);

    /** 取消订单 */
    OrderMasterDto cancel(String openId, String orderId);

}
