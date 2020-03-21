package com.st.springboot.controller;


import com.lly835.bestpay.rest.type.Post;
import com.st.springboot.dataobject.ProductCategory;
import com.st.springboot.dataobject.ProductInfo;
import com.st.springboot.exception.SellException;
import com.st.springboot.form.ProductFrom;
import com.st.springboot.service.impl.CategoryServiceImpl;
import com.st.springboot.service.impl.InfoServiceImpl;
import com.st.springboot.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * Created by 廖师兄
 * 2017-07-23 15:12
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private InfoServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 列表
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.onsale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "http://sellst.natapp1.cc/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam("productId") String productId,
                                 Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "http://sellst.natapp1.cc/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品新增和修改
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {

        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    /**
     * 商品修改后表单的提交和保存
     *
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductFrom form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        try {
            //如果ProdcutId为空，就是新增
            ProductInfo productInfo = new ProductInfo();
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genKeyUtil());
            }

            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "http://sellst.natapp1.cc/sell/seller/product/list");

        return new ModelAndView("common/success", map);
    }

}
