package com.st.springboot.repository;

import com.st.springboot.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-12 22:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void FindOneTest01() {
        List<ProductCategory> productCategoryList = repository.findAll();
        for (ProductCategory productCategory : productCategoryList) {
            System.out.println(productCategory.toString());
        }

        ProductCategory productCategory = repository.findById(1).get();
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest() {
//        ProductCategory productCategory = repository.findById(4).get();
//        productCategory.setCategoryName("女装大哥");

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女装大佬");
        productCategory.setCategoryType(5);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        List<ProductCategory> productCategoryList = repository.findByCategoryTypeIn(list);

        for (ProductCategory productCategory : productCategoryList) {
            System.out.println(productCategory.toString());
        }

        Assert.assertNotEquals(0, productCategoryList.size());
    }

}