package com.st.springboot.service;

import com.st.springboot.dataobject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-16 20:46
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIn);

    ProductCategory save(ProductCategory productCategory);

}
