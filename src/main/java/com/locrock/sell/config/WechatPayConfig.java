package com.locrock.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Author : 周怡斌 data : 2018/9/8
 */
@Component
public class WechatPayConfig
{
    @Autowired
    private AccountConfig config;

    @Bean
    public BestPayServiceImpl bestPayService(){

        BestPayServiceImpl bestPayService=new BestPayServiceImpl ();

        bestPayService.setWxPayH5Config (wxPayH5Config());

        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config wxPayH5Config=new WxPayH5Config ();
        wxPayH5Config.setAppId (config.getMyAppId ());
        wxPayH5Config.setAppSecret (config.getMyAppSecret ());
        wxPayH5Config.setKeyPath (config.getKeyPath ());
        wxPayH5Config.setMchId (config.getMchId ());
        wxPayH5Config.setMchKey (config.getMchKey ());
        wxPayH5Config.setNotifyUrl (config.getNotifyUrl ());
        return wxPayH5Config;
    }

}
