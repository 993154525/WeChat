package com.st.springboot.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.st.springboot.dto.OrderMasterDto;

/**
 * 支付
 * Created by 廖师兄
 * 2017-07-04 00:53
 */
public interface PayService {

    PayResponse create(OrderMasterDto orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderMasterDto orderDTO);
}
