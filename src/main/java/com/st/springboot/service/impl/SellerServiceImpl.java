package com.st.springboot.service.impl;

import com.st.springboot.dataobject.SellerInfo;
import com.st.springboot.repository.SellerInfoRepository;
import com.st.springboot.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shaotian
 * @create 2020-03-20 10:16
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findbySellerOpenid(String openId) {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenId(openId);

        return sellerInfo;
    }

}
