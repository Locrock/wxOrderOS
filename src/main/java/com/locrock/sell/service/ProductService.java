package com.locrock.sell.service;

import com.locrock.sell.dataObject.ProductInfo;
import com.locrock.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
public interface ProductService
{
    ProductInfo findOne(String productId);

    /*查询所有上架商品列表*/
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //件库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
