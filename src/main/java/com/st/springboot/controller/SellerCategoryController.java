package com.st.springboot.controller;

import com.lly835.bestpay.rest.type.Get;
import com.st.springboot.dataobject.ProductCategory;
import com.st.springboot.dataobject.ProductInfo;
import com.st.springboot.form.CategoryForm;
import com.st.springboot.form.ProductFrom;
import com.st.springboot.service.impl.CategoryServiceImpl;
import com.st.springboot.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author shaotian
 * @create 2020-03-19 15:05
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 类目列表
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {

        List<ProductCategory> categoryServiceAll = categoryService.findAll();
        map.put("categoryServiceAll", categoryServiceAll);

        return new ModelAndView("category/list", map);
    }

    /**
     * 类目修改界面
     *
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) String categoryId,
                              Map<String, Object> map) {

        if (!StringUtils.isEmpty(categoryId)) {
            ProductCategory category = categoryService.findOne(Integer.valueOf(categoryId));
            map.put("category", category);
        }

        return new ModelAndView("category/index", map);
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
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        try {
            //如果ProdcutId为空，就是新增
            ProductCategory category = new ProductCategory();
            if (!StringUtils.isEmpty(form.getCategoryId())) {
            }
            BeanUtils.copyProperties(form, category);
            categoryService.save(category);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "http://sellst.natapp1.cc/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "http://sellst.natapp1.cc/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }

}
