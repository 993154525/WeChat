package com.st.springboot.service.impl;

import com.st.springboot.dataobject.ProductCategory;
import com.st.springboot.repository.ProductCategoryRepository;
import com.st.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-16 20:52
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {

        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIn) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeIn);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {

        return categoryRepository.save(productCategory);
    }
}
