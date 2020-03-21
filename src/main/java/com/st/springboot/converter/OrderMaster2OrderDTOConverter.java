package com.st.springboot.converter;

import com.st.springboot.dataobject.OrderMaster;
import com.st.springboot.dto.OrderMasterDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-21 10:47
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderMasterDto convert(OrderMaster orderMaster) {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        BeanUtils.copyProperties(orderMasterDto, orderMaster);

        return orderMasterDto;
    }

    public static List<OrderMasterDto> convert(List<OrderMaster> orderMasterList) {
        List<OrderMasterDto> orderMasterDtoList = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasterList) {
            OrderMasterDto orderMasterDto = new OrderMasterDto();
            BeanUtils.copyProperties(orderMaster, orderMasterDto);
            orderMasterDtoList.add(orderMasterDto);
        }
        return orderMasterDtoList;
//        return orderMasterList.stream().map(e ->
//                convert(e)
//        ).collect(Collectors.toList());

    }

}
