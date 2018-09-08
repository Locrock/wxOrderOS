package com.locrock.sell.controller;

import com.locrock.sell.dto.OrderDTO;
import com.locrock.sell.enums.ResultEnum;
import com.locrock.sell.exception.SellException;
import com.locrock.sell.service.OrderService;
import com.locrock.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : 周怡斌 data : 2018/9/8
 */
@RestController
@RequestMapping("pay")
@Slf4j
public class PayController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("create")
    public void create(@RequestParam("orderId")String orderId,
                       @RequestParam("returnUrl")String returnUrl){
        OrderDTO orderDTO = orderService.findOne (orderId);

        if (orderDTO==null) throw new SellException (ResultEnum.ORDER_NOT_EXIST);

        //发起支付
        payService.create (orderDTO);
    }
}
