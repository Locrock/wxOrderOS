package com.locrock.sell.repository;

import com.locrock.sell.dataObject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest
{
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void InsertOrderDetail(){
        OrderDetail detail=new OrderDetail ();

        detail.setDetailId ("3");
        detail.setOrderId ("1234568");
        detail.setProductIcon ("http://www.bilibili.com");
        detail.setProductId ("234567");
        detail.setProductName ("酸菜粥");
        detail.setProductPrice (new BigDecimal (10));
        detail.setProductQuantity (1);

        OrderDetail orderDetail = repository.save (detail);

        Assert.assertNotNull (orderDetail);
    }

    @Test
    public void findByOrOrderId ()
    {
        List<OrderDetail> details = repository.findByOrOrderId ("123456");

        System.out.println ("details = " + details);
    }
}