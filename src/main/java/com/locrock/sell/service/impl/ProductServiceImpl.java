package com.locrock.sell.service.impl;

import com.locrock.sell.dataObject.ProductInfo;
import com.locrock.sell.enums.ProductStatus;
import com.locrock.sell.repository.ProductInfoRepository;
import com.locrock.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductInfoRepository infoRepository;

    @Override
    public ProductInfo findOne (String productId)
    {
        return infoRepository.findOne (productId);
    }

    @Override
    public List<ProductInfo> findUpAll ()
    {
        return infoRepository.findByProductStatus (ProductStatus.UP.getCode ());
    }

    @Override
    public Page<ProductInfo> findAll (Pageable pageable)
    {
        return infoRepository.findAll (pageable);
    }

    @Override
    public ProductInfo save (ProductInfo productInfo)
    {
        return infoRepository.save (productInfo);
    }
}
