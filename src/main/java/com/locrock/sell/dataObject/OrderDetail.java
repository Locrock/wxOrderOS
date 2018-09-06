package com.locrock.sell.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    /*订单详情id*/
    private String detailId;
    /*订单id*/
    private String orderId;
    /*商品id*/
    private String productId;
    /*商品名称*/
    private String productName;
    /*商品价格*/
    private BigDecimal productPrice;
    /*商品数量*/
    private Integer productQuantity;
    /*商品图标*/
    private String productIcon;
}
