package com.st.springboot.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author shaotian
 * @create 2020-02-17 10:42
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String CategoryName;

    @JsonProperty("type")
    private Integer CategoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOS;

}
