package com.locrock.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import com.locrock.sell.config.WechatPayConfig;
import com.locrock.sell.dto.OrderDTO;
import com.locrock.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author : 周怡斌 data : 2018/9/8
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService
{
    private static final String ORDER_NAME="微信点餐";
    @Autowired
    private WechatPayConfig payConfig;


    @Override
    public void create (OrderDTO orderDTO)
    {
        PayRequest request=new PayRequest ();
        request.setOpenid ("oFRZE0U1p3mIC106okwNK-K3yXxI");
        request.setOrderAmount (orderDTO.getOrderAmount ().doubleValue ());
        request.setOrderId (orderDTO.getOrderId ());
        request.setOrderName (ORDER_NAME);
        request.setPayTypeEnum (BestPayTypeEnum.WXPAY_H5);
        BestPayServiceImpl payService = payConfig.bestPayService ();

        log.info ("[发起订单] payRequest={}", JsonUtil.toJson (request));

        PayResponse payResponse = payService.pay (request);

        log.info ("[支付详情]  返回情况 , payResponse={}",JsonUtil.toJson (payResponse));

    }
}
