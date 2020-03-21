package com.st.springboot.repository;

import com.st.springboot.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shaotian
 * @create 2020-03-19 21:43
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenId(String openid);

}
