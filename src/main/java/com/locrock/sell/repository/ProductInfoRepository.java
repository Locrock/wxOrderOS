package com.locrock.sell.repository;

import com.locrock.sell.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>
{
    List<ProductInfo> findByProductStatus(Integer status);
}
