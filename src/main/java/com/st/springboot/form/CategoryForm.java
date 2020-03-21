package com.st.springboot.form;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author shaotian
 * @create 2020-03-19 15:51
 */
@Data
@DynamicUpdate
public class CategoryForm {

    /**
     * 类目id.
     */
    @Id
    private Integer categoryId;

    /**
     * 类目名字.
     */
    private String categoryName;

    /**
     * 类目编号.
     */
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
