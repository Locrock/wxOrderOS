package com.locrock.sell.service.impl;

import com.locrock.sell.dataObject.ProductInfo;
import com.locrock.sell.dto.CartDTO;
import com.locrock.sell.enums.ProductStatus;
import com.locrock.sell.enums.ResultEnum;
import com.locrock.sell.exception.SellException;
import com.locrock.sell.repository.ProductInfoRepository;
import com.locrock.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void increaseStock (List<CartDTO> cartDTOList)
    {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo info = infoRepository.findOne (cartDTO.getProductId ());

            if (info==null) throw new SellException (ResultEnum.PRODUCT_NOT_EXIST);

            info.setProductStock (info.getProductStock ()+cartDTO.getProductQuantity ());

            infoRepository.save (info);
//            if (info.getProductStock ()<0) throw new RuntimeException ("商品超卖");
        }
    }

    @Override
    @Transactional
    public void decreaseStock (List<CartDTO> cartDTOList)
    {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo info = infoRepository.findOne (cartDTO.getProductId ());

            if (info==null) throw new SellException (ResultEnum.PRODUCT_NOT_EXIST);

            info.setProductStock (info.getProductStock ()-cartDTO.getProductQuantity ());

            if (info.getProductStock ()<0) throw new SellException (ResultEnum.PRODUCT_STOCK_ERROR);

            infoRepository.save (info);
        }
    }
}
