package com.locrock.sell.service.impl;

import com.locrock.sell.dataObject.OrderDetail;
import com.locrock.sell.dataObject.OrderMaster;
import com.locrock.sell.dto.OrderDTO;
import com.locrock.sell.enums.PayStatusEnum;
import com.locrock.sell.repository.OrderDetailRepository;
import com.locrock.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void create ()
    {
        OrderDTO orderDTO=new OrderDTO ();

        orderDTO.setBuyerAddress("广州");
        orderDTO.setOrderId("123456");
        orderDTO.setBuyerOpenid("123456");
        orderDTO.setBuyerName("周怡斌");
        orderDTO.setBuyerPhone("18200831730");
        orderDTO.setOrderAmount(new BigDecimal (1));

        List<OrderDetail> detailList = orderDetailRepository.findAll ();

        orderDTO.setOrderDetailList (detailList);

        OrderDTO dto = orderService.create (orderDTO);

        Assert.assertNotNull (dto);

        System.out.println ("dto = " + dto);
    }

    @Test
    public void findOne ()
    {
        OrderDTO orderDTO = orderService.findOne ("1536312345692");

        log.error (orderDTO.toString ());
    }

    @Test
    public void findList ()
    {
        PageRequest pageRequest=new PageRequest (0,2);

        Page<OrderDTO> list = orderService.findList ("123456", pageRequest);

        Assert.assertNotEquals (0,list.getTotalElements ());

        System.err.println("list = " + list.getContent ());
    }

    @Test
    public void cancel ()
    {
        OrderDTO dto = orderService.findOne ("1536312345692");

        OrderDTO orderDTO = orderService.cancel (dto);

        Assert.assertNotEquals (orderDTO,dto);
    }

    @Test
    public void finish ()
    {
        OrderDTO dto = orderService.findOne ("1536312345692");

        OrderDTO orderDTO = orderService.finish (dto);

        Assert.assertNotEquals (orderDTO,dto);
    }

    @Test
    public void paid ()
    {
        OrderDTO dto = orderService.findOne ("1536312345692");

        OrderDTO orderDTO = orderService.paid (dto);

        Assert.assertEquals (orderDTO.getPayStatus (), PayStatusEnum.SUCCESS.getCode ());
    }
}