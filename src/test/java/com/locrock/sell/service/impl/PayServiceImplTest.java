package com.locrock.sell.service.impl;

import com.locrock.sell.dto.OrderDTO;
import com.locrock.sell.service.OrderService;
import com.locrock.sell.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith (SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest
{
    @Autowired
    private PayService service;
    @Autowired
    private OrderService orderService;
    @Test
    public void create ()
    {
        OrderDTO orderDTO = orderService.findOne ("1536327853348");

        service.create (orderDTO);
    }
}