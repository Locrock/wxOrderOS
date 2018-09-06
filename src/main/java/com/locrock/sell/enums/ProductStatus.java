package com.locrock.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
//商品状态
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProductStatus
{
    UP(0,"上架"),
    DOWN(1,"下架");


    private Integer code;
    private String message;
}
