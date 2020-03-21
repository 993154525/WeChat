package com.st.springboot.service;

import com.st.springboot.dataobject.SellerInfo;

/**
 * @author shaotian
 * @create 2020-03-20 10:15
 */
public interface SellerService {

    SellerInfo findbySellerOpenid(String openId);

}
