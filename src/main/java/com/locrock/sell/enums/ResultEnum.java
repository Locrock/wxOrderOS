package com.locrock.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author : 周怡斌 data : 2018/9/7
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum
{
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单无法被取消"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_FINISHED_ERROR(17,"订单完结失败"),
    ORDER_PAID_ERROR(18,"订单支付失败"),
    CART_EMPTY_ERROR(19,"购物车为空"),
    CREATE_ORDER_ERROR(20,"创建购物车失败"),
    OPENID_EMPTY(21,"openid为空"),
    ORDER_ID_EMPTY(22,"订单id为空");

    private Integer code;
    private String message;
}
