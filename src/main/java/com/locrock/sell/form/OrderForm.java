package com.locrock.sell.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Author : 周怡斌 data : 2018/9/7
 * 接收浏览器传来的参数
 * 进行表单验证
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm
{
    /*买家姓名*/
    @NotEmpty(message = "姓名必填")
    private String name;

    /*买家手机号码*/
    @NotEmpty(message = "手机号必填")
    private String phone;

    /*卖家地址*/
    @NotEmpty(message = "地址必填")
    private String address;

    /*卖家openid*/
    @NotEmpty(message = "openid必填")
    private String openid;

    /*购物车json字符串*/
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
