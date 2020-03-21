package com.st.springboot.service.impl;

import com.st.springboot.converter.OrderMaster2OrderDTOConverter;
import com.st.springboot.dataobject.OrderDetail;
import com.st.springboot.dataobject.OrderMaster;
import com.st.springboot.dataobject.ProductInfo;
import com.st.springboot.dto.CartDto;
import com.st.springboot.dto.OrderMasterDto;
import com.st.springboot.enums.OrderMasterEnums;
import com.st.springboot.enums.OrderMasterPayEnums;
import com.st.springboot.enums.ResultEnums;
import com.st.springboot.exception.SellException;
import com.st.springboot.repository.OrderDetailRepository;
import com.st.springboot.repository.OrderMasterRepository;
import com.st.springboot.repository.ProductInfoRepository;
import com.st.springboot.service.InfoService;
import com.st.springboot.service.OrderService;
import com.st.springboot.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shaotian
 * @create 2020-02-20 19:44
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private InfoServiceImpl infoService;

    @Override
    @Transactional
    public OrderMasterDto create(OrderMasterDto orderMasterDto) {

        BigDecimal Orderamout = new BigDecimal(0);
        String orderid = KeyUtil.genKeyUtil();

        //1、查询商品(数量，价格)
        for (OrderDetail orderDetail : orderMasterDto.getOrderDetailList()) {
            ProductInfo productInfo = productInfoRepository.findById(orderDetail.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }

            //2、计算总价
            Orderamout = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(Orderamout);

            //订单详情入库
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.genKeyUtil());
            orderDetail.setOrderId(orderid);
            orderDetailRepository.save(orderDetail);
        }

        //3、写入订单数据
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDto.setOrderId(orderid);
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        orderMaster.setOrderAmount(Orderamout);
        orderMaster.setOrderStatus(OrderMasterEnums.New.getCode());
        orderMaster.setPayStatus(OrderMasterPayEnums.PayWait.getCode());
        orderMasterRepository.save(orderMaster);

        //4、扣减库存时验证库存
        List<CartDto> cartDtoList = orderMasterDto.getOrderDetailList().stream().map
                (e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        infoService.decreaseStock(cartDtoList);

        return orderMasterDto;
    }

    @Override
    public OrderMasterDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnums.ORDER_MASTER_ERROR);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());

        OrderMasterDto orderMasterDto = new OrderMasterDto();
        BeanUtils.copyProperties(orderMaster, orderMasterDto);
        orderMasterDto.setOrderDetailList(orderDetailList);

        return orderMasterDto;
    }

    @Override
    public Page<OrderMasterDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> byBuyerOpenid = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMasterDto> convert = OrderMaster2OrderDTOConverter.convert(byBuyerOpenid.getContent());
        Page<OrderMasterDto> orderMasterDto = new PageImpl<OrderMasterDto>(convert, pageable, byBuyerOpenid.getTotalElements());

        return orderMasterDto;
    }

    @Override
    public OrderMasterDto cancel(OrderMasterDto orderMasterDto) {

        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderMasterDto.getOrderStatus().equals(OrderMasterEnums.New.getCode())) {
            log.error("[取消订单]订单状态不正确，orderid={},orderStutus={}", orderMasterDto.getOrderId(), orderMasterDto.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }

        //改变订单状态
        orderMasterDto.setOrderStatus(OrderMasterEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[取消订单]更新失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderMasterDto.getOrderDetailList())) {
            throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
        }
        List<CartDto> cartDtoList = orderMasterDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        infoService.increaseStock(cartDtoList);


        //退回金额
        //TODO

        return null;
    }

    @Override
    public OrderMasterDto finish(OrderMasterDto orderMasterDto) {
        //判断订单状态
        if (!orderMasterDto.getOrderStatus().equals(OrderMasterEnums.New.getCode())) {
            log.error("[订单关闭错误],orderId={},orderStutus={}", orderMasterDto.getOrderId(), orderMasterDto.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderMasterDto.setOrderStatus(OrderMasterEnums.FiNSH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        orderMasterRepository.save(orderMaster);

        return orderMasterDto;
    }

    @Override
    public OrderMasterDto paid(OrderMasterDto orderMasterDto) {
        //判断订单状态
        if (!orderMasterDto.getPayStatus().equals(OrderMasterPayEnums.PayWait.getCode())) {
            log.error("[订单支付错误],orderId={},orderStutus={}", orderMasterDto.getOrderId(), orderMasterDto.getPayStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }

        //修改订单支付状态
        orderMasterDto.setPayStatus(OrderMasterPayEnums.PaySuccess.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        orderMasterRepository.save(orderMaster);

        return orderMasterDto;
    }

    @Override
    public Page<OrderMasterDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findAll(pageable);
        List<OrderMasterDto> convert = OrderMaster2OrderDTOConverter.convert(orderMasters.getContent());
        Page<OrderMasterDto> orderMasterDto = new PageImpl<OrderMasterDto>(convert, pageable, orderMasters.getTotalElements());

        return orderMasterDto;
    }
}
