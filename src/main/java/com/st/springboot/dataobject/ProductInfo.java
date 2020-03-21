package com.st.springboot.dataobject;

import com.st.springboot.enums.CodeEnum;
import com.st.springboot.enums.ProductInfoEnums;
import com.st.springboot.enums.ProductStatusEnum;
import com.st.springboot.util.serializer.CodeEnumUtil;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.apache.commons.lang3.EnumUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shaotian
 * @create 2020-02-16 21:14
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    @Id
    private String productId;

    /**
     * 名字
     **/
    private String productName;

    /**
     * 单价
     **/
    private BigDecimal productPrice;

    /**
     * 库存
     **/
    private Integer productStock;

    /**
     * 描述
     **/
    private String productDescription;

    /**
     * 图片
     **/
    private String productIcon;

    /**
     * 商品状态 0正常1下架
     **/
    private Integer productStatus = ProductInfoEnums.UP.getCode();

    /**
     * 类目编号
     **/
    private Integer categoryType;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单最近修改时间
     */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return CodeEnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
