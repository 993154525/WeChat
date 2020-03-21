package com.st.springboot.service.impl;

import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.enums.ResultEnums;
import com.st.springboot.exception.SellException;
import com.st.springboot.service.BuyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shaotian
 * @create 2020-03-09 21:27
 */

@Slf4j
@Service
public class BuyServiceImpl implements BuyService {

    @Autowired
    private OrderServiceImpl orderService;

    @Override
    public OrderMasterDto findone(String openId, String orderId) {
        OrderMasterDto orderServiceOne = orderService.findOne(orderId);

        if (!orderServiceOne.getBuyerOpenid().equals(openId)) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ResultEnums.ORDER_DETAIL_ERROR);
        }

        return orderService.cancel(orderServiceOne);
    }

    @Override
    public OrderMasterDto cancel(String openId, String orderId) {
        OrderMasterDto orderMasterDto = findone(openId, orderId);

        if (orderMasterDto == null) {
            return null;
        }

        return null;
    }
}
