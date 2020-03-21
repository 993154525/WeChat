package com.st.springboot.controller;

import com.st.springboot.VO.ProductInfoVO;
import com.st.springboot.VO.ProductVO;
import com.st.springboot.VO.ResultVO;
import com.st.springboot.dataobject.ProductCategory;
import com.st.springboot.dataobject.ProductInfo;
import com.st.springboot.service.CategoryService;
import com.st.springboot.service.InfoService;
import com.st.springboot.service.impl.CategoryServiceImpl;
import com.st.springboot.service.impl.InfoServiceImpl;
import com.st.springboot.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 *
 * @author shaotian
 * @create 2020-02-17 10:06
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private InfoServiceImpl infoServicel;

    @GetMapping("/list")
    public ResultVO list() {



        //1、查询所有上架的商品
        List<ProductInfo> productInfoList = infoServicel.findUpAll();

        //2、查询所有的类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> categories = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3、数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : categories) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOS(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);


    }

}
