package com.locrock.sell.repository;

import com.locrock.sell.dataObject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest
{
    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveInfo(){
        ProductInfo info=new ProductInfo ();

        info.setProductDescription ("好吃");
        info.setProductIcon ("adfsdfsd fsdfs");
        info.setProductId ("123456");
        info.setProductName ("皮蛋粥");
        info.setProductPrice (new BigDecimal (10));
        info.setProductStock (100);
        info.setCategoryType (1);
        info.setProductStatus (0);

        ProductInfo productInfo = repository.save (info);

        System.out.println (productInfo);

        Assert.assertNotNull (productInfo);
    }


    @Test
    public void findByProductStatus ()
    {
        List<ProductInfo> byProductStatus = repository.findByProductStatus (0);

        System.out.println (byProductStatus);

    }
}