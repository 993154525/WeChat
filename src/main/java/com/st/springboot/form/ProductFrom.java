package com.st.springboot.form;

import com.st.springboot.enums.ProductStatusEnum;
import com.st.springboot.util.serializer.CodeEnumUtil;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shaotian
 * @create 2020-03-19 13:54
 */
@Entity
@DynamicUpdate
@Data
public class ProductFrom {

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



}
