package com.st.springboot.repository;

import com.st.springboot.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-18 21:15
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String openid);

}
