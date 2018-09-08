package com.locrock.sell.controller;

import com.locrock.sell.enums.ResultEnum;
import com.locrock.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * Author : 周怡斌 data : 2018/9/8
 */
@Controller
@Slf4j
@RequestMapping("wechat")
public class WechatController
{
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
        // 1 . 配置
        /**注入配置文件*/
        // 2 . 调用方法
        //先写死,后期改成配置文件配置
        String url="http://locrock.nat300.top/sell/wechat/userInfo";

        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl (url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode (returnUrl));

        log.info ("[微信网页授权获取code] 获取code , result={}",redirectUrl);

        return "redirect:"+redirectUrl;
    }

    @GetMapping("userInfo")
    public String userInfo(@RequestParam("code")String code,
                         @RequestParam("state")String returnUrl)
    {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken ();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken (code);
        } catch (WxErrorException e) {
            log.error ("[微信网页授权] {}", e);
            throw new SellException (ResultEnum.WX_MP_ERROR.getCode (), e.getError ().getErrorMsg ());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId ();

        log.info ("[openid] openid={}",openId);

        return "redirect:"+returnUrl+"?openid="+openId;
    }
}
