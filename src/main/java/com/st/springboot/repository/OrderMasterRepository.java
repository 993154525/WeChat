package com.st.springboot.repository;

import com.st.springboot.dataobject.OrderMaster;
import freemarker.cache.OrMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author shaotian
 * @create 2020-02-18 21:11
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String openid, Pageable pageable);

    Page<OrderMaster> findByBuyerNameLike(String buyerName, Pageable pageable);

}
