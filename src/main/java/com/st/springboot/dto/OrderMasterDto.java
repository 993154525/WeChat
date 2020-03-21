package com.st.springboot.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.st.springboot.dataobject.OrderDetail;
import com.st.springboot.enums.OrderMasterEnums;
import com.st.springboot.enums.OrderMasterPayEnums;
import com.st.springboot.util.serializer.CodeEnumUtil;
import com.st.springboot.util.serializer.Date2LongSerializer;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.apache.commons.lang3.EnumUtils;

import javax.ws.rs.PUT;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-20 19:04
 */
@Data
public class OrderMasterDto {
    /**
     * 买家id
     */
    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;

    /**
     * 买家手机号.
     */
    private String buyerPhone;

    /**
     * 买家地址.
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus;

    /**
     * 订单创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 订单最近修改时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @JsonIgnore
    public OrderMasterEnums orderMasterEnums() {
        return CodeEnumUtil.getByCode(orderStatus, OrderMasterEnums.class);
    }

    @JsonIgnore
    public OrderMasterPayEnums orderMasterPayEnums() {
        return CodeEnumUtil.getByCode(payStatus, OrderMasterPayEnums.class);
    }

}
