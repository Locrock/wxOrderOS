package com.locrock.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : 周怡斌 data : 2018/9/7
 * 购物车
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO
{
    //商品id
    private String productId;
    //商品数量
    private Integer productQuantity;
}
