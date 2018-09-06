package com.locrock.sell.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo
{
    @Id
    private String productId;
    /*名字*/
    private String productName;
    /*价格*/
    private BigDecimal productPrice;
    /*库存*/
    private Integer productStock;
    /*商品描述*/
    private String productDescription;
    /*商品图标*/
    private String productIcon;
    /*商品状态*/
    /*0:正常,1:下架*/
    private Integer productStatus;
    /*商品类型*/
    private Integer categoryType;
}
