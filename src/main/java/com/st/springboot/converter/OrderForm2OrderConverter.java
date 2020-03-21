package com.st.springboot.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st.springboot.dataobject.OrderDetail;
import com.st.springboot.dataobject.OrderMaster;
import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.enums.ResultEnums;
import com.st.springboot.exception.SellException;
import com.st.springboot.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaotian
 * @create 2020-03-07 16:51
 */
@Slf4j
public class OrderForm2OrderConverter {

    public static OrderMasterDto converter(OrderForm orderForm) {

        Gson gson = new Gson();

        OrderMasterDto orderMasterDto = new OrderMasterDto();

        orderMasterDto.setBuyerName(orderForm.getName());
        orderMasterDto.setBuyerAddress(orderForm.getAddress());
        orderMasterDto.setBuyerPhone(orderForm.getPhone());
        orderMasterDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("[对象转换错误],orderItems", orderForm.getItems());
            throw new SellException(ResultEnums.PARAM_ORROR);
        }
        orderMasterDto.setOrderDetailList(orderDetailList);

        return orderMasterDto;

    }

}
