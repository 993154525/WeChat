package com.st.springboot.service.impl;

import com.st.springboot.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author shaotian
 * @create 2020-02-16 20:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory categoryServiceOne = categoryService.findOne(1);
        System.out.println(categoryServiceOne.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> categoryServiceAll = categoryService.findAll();
        System.out.println(categoryServiceAll.toString());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categoryTypeIn = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        for (ProductCategory productCategory : categoryTypeIn) {
            System.out.println(productCategory.toString());
        }
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男人无敌");
        productCategory.setCategoryType(10);
        categoryService.save(productCategory);
    }
}