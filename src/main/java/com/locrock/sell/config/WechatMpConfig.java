package com.locrock.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Author : 周怡斌 data : 2018/9/8
 */
@Component
public class WechatMpConfig
{
    @Autowired
    private AccountConfig accountConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService=new WxMpServiceImpl ();

        wxMpService.setWxMpConfigStorage (wxMpConfigStorage ());

        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage configStorage=new WxMpInMemoryConfigStorage ();

        configStorage.setAppId (accountConfig.getMyAppId ());
        configStorage.setSecret (accountConfig.getMyAppSecret ());

        return configStorage;
    }
}
