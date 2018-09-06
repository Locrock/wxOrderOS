package com.locrock.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品包含类目
 * Author : 周怡斌 data : 2018/9/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO<T>
{
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("food")
    private List<ProductInfoVO> productInfoVOList;
}
