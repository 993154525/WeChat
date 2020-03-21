package com.st.springboot.controller;

import com.lly835.bestpay.model.PayResponse;
import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.enums.ResultEnums;
import com.st.springboot.exception.SellException;
import com.st.springboot.service.OrderService;
import com.st.springboot.service.PayService;
import com.st.springboot.service.impl.OrderServiceImpl;
import com.st.springboot.service.impl.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author shaotian
 * @create 2020-03-12 14:15
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private PayServiceImpl payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) throws UnsupportedEncodingException {
        //1. 查询订单
        OrderMasterDto orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }

        //2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl.startsWith("http://") ? returnUrl : URLEncoder.encode(returnUrl, "utf-8"));

        return new ModelAndView("pay/create", map);
    }

    /**
     * 微信异步通知
     *
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
