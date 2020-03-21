package com.st.springboot.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.service.PayService;
import com.st.springboot.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shaotian
 * @create 2020-03-12 14:20
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "湘菜订单";

    @Autowired
    private BestPayServiceImpl bestPayServicel;

    @Override
    public PayResponse create(OrderMasterDto orderDTO) {
        PayRequest request = new PayRequest();
        request.setOpenid(orderDTO.getBuyerOpenid());
        request.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        request.setOrderId(orderDTO.getOrderId());
        request.setOrderName(ORDER_NAME);
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付] request = {}", JSONUtil.toJson(request));

        PayResponse payResponse = bestPayServicel.pay(request);
        log.info("[微信支付] response = {}", JSONUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        return null;
    }

    @Override
    public RefundResponse refund(OrderMasterDto orderDTO) {
        return null;
    }
}
