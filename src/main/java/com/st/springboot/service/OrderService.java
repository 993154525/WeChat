package com.st.springboot.service;

import com.st.springboot.dataobject.OrderMaster;
import com.st.springboot.dto.OrderMasterDto;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author shaotian
 * @create 2020-02-20 19:22
 */
public interface OrderService {

    /**
     * 创建新订单
     */
    OrderMasterDto create(OrderMasterDto orderMasterDto);

    /**
     * 查询新订单
     */
    OrderMasterDto findOne(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderMasterDto> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderMasterDto cancel(OrderMasterDto orderMasterDto);

    /**
     * 完结订单
     */
    OrderMasterDto finish(OrderMasterDto orderMasterDto);

    /**
     * 支付订单
     */
    OrderMasterDto paid(OrderMasterDto orderMasterDto);

    /**
     * 查询订单列表
     **/
    Page<OrderMasterDto> findList(Pageable pageable);

}
