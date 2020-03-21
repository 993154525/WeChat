package com.st.springboot.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author shaotian
 * @create 2020-03-19 21:39
 */
@Entity
@DynamicUpdate
@Data
public class SellerInfo {

    @Id
    private String sellerId;

    private String userName;

    private String passWord;

    private String openId;

    private String createTime;

    private String updateTime;
}
