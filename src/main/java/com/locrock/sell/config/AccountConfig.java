package com.locrock.sell.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author : 周怡斌 data : 2018/9/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "wechat")
public class AccountConfig
{
    private String myAppId;

    private String myAppSecret;
    /*商户号*/
    private String mchId;
    /*商户秘钥*/
    private String mchKey;
    /*路径*/
    private String keyPath;
    /*回调地址*/
    private String notifyUrl;
}
