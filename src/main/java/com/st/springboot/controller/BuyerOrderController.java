package com.st.springboot.controller;

import com.st.springboot.VO.ResultVO;
import com.st.springboot.converter.OrderForm2OrderConverter;
import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.enums.ResultEnums;
import com.st.springboot.exception.SellException;
import com.st.springboot.form.OrderForm;
import com.st.springboot.service.impl.BuyServiceImpl;
import com.st.springboot.service.impl.OrderServiceImpl;
import com.st.springboot.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shaotian
 * @create 2020-03-06 20:25
 */
@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private BuyServiceImpl buyService;

    //创建订单
    @RequestMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {

        /** 验证方法 */
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderFrom={}", orderForm);
            throw new SellException(ResultEnums.PARAM_ORROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMasterDto orderMasterDto = OrderForm2OrderConverter.converter(orderForm);

        if (CollectionUtils.isEmpty(orderMasterDto.getOrderDetailList())) {
            log.error("创建订单,[购物车不能为空】");
            throw new SellException(ResultEnums.CART_EMPTY);
        }

        OrderMasterDto masterDto = orderService.create(orderMasterDto);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("openid", masterDto.getBuyerOpenid());

        return ResultVOUtil.success(masterDto);
    }

    //订单列表
    @RequestMapping("/list")
    public ResultVO<List<OrderMasterDto>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if (StringUtils.isEmpty(openid)) {
            log.error("[订单对象为空] openid", openid);
            throw new SellException(ResultEnums.ORDER_DETAIL_ERROR);
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderMasterDto> orderMasterDtoPage = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(orderMasterDtoPage.getContent());
    }

    //订单详情
    @RequestMapping("/detail")
    public ResultVO<List<OrderMasterDto>> detail(@RequestParam("openId") String openid,
                                                 @RequestParam("orderId") String orderid) {

        if (StringUtils.isEmpty(openid)) {
            log.error("[订单对象为空] openid", openid);
            throw new SellException(ResultEnums.ORDER_DETAIL_ERROR);
        }

        OrderMasterDto orderMasterDto = buyService.findone(openid, orderid);

        return ResultVOUtil.success(orderMasterDto);
    }

    //取消订单
    @RequestMapping("/cancel")
    public ResultVO<List<OrderMasterDto>> cancel(@RequestParam("openid") String openid,
                                                 @RequestParam("orderid") String orderid) {

        OrderMasterDto cancel = buyService.cancel(openid, orderid);

        return ResultVOUtil.success(cancel);
    }
}
