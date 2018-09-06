package com.locrock.sell.repository;

import com.locrock.sell.dataObject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save(){
        OrderMaster master=new OrderMaster();
        master.setBuyerAddress("广州");
        master.setOrderId("123456");
        master.setBuyerOpenid("123456");
        master.setBuyerName("周怡斌");
        master.setBuyerPhone("18200831730");
        master.setOrderAmount(new BigDecimal(1));

        repository.save(master);
    }
    @Test
    public void findByBuyerOpenid() {
        PageRequest request=new PageRequest(0,2);

        Page<OrderMaster> page = repository.findByBuyerOpenid("locrock", request);

        System.out.println(page.getContent());
    }
}