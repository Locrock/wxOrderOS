package com.locrock.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.locrock.sell.dataObject.OrderDetail;
import com.locrock.sell.util.serializer.Date2LongSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author : 周怡斌 data : 2018/9/7
 *  页面传输使用的OrderMaster对象,新增了orderDetail的订单详情集合
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
// 当数据存在控制不需要返回的时候使用此注解,
// 在配置文件中配置全局配置
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderDTO
{
    /*订单id*/
    private String orderId;
    /*买家名字*/
    private String buyerName;
    /*买家电话*/
    private String buyerPhone;
    /*买家地址*/
    private String buyerAddress;
    /*买家的微信id*/
    private String buyerOpenid;
    /*账单金额*/
    private BigDecimal orderAmount;
    /*订单状态,默认新订单*/
    private Integer orderStatus;
    /*支付状态,默认未支付*/
    private Integer payStatus;
    /*创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /*更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /*订单详情集合*/
    private List<OrderDetail> orderDetailList;
}
