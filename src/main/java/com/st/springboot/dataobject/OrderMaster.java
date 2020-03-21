package com.st.springboot.dataobject;

import com.st.springboot.enums.OrderMasterEnums;
import com.st.springboot.enums.OrderMasterPayEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shaotian
 * @create 2020-02-18 20:51
 */
@Data
@DynamicUpdate
@Entity
public class OrderMaster {

    /**
     * 买家id
     */
    @Id
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
    private Integer orderStatus = OrderMasterEnums.New.getCode();

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus = OrderMasterPayEnums.PayWait.getCode();

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单最近修改时间
     */
    private Date updateTime;

}
