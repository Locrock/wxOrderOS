package com.locrock.sell.service.impl;

import com.locrock.sell.dataObject.ProductInfo;
import com.locrock.sell.enums.ProductStatus;
import com.locrock.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith (SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest
{

    @Autowired
    private ProductService productService;

    @Test
    public void findOne ()
    {
        ProductInfo productInfo = productService.findOne ("123456");

        Assert.assertNotNull (productInfo);

        log.error (productInfo.toString ());
    }

    @Test
    public void findUpAll ()
    {
        List<ProductInfo> all = productService.findUpAll ();

        log.error (all.toString ());
    }

    @Test
    public void findAll ()
    {
        PageRequest pageRequest=new PageRequest (0,2);

        Page<ProductInfo> all = productService.findAll (pageRequest);

        System.out.println (all.getTotalElements ());
        log.error (all.getContent ().toString ());
    }

    @Test
    public void save ()
    {
        ProductInfo info=new ProductInfo ();

        info.setProductDescription ("也很好吃");
        info.setProductIcon ("http://www.baidu.com");
        info.setProductId ("234567");
        info.setProductName ("酸菜粥");
        info.setProductPrice (new BigDecimal (10));
        info.setProductStock (100);
        info.setCategoryType (1);
        info.setProductStatus (ProductStatus.UP.getCode ());

        ProductInfo save = productService.save (info);

        System.out.println ("save = " + save);
    }
}