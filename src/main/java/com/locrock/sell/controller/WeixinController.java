package com.locrock.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Author : 周怡斌 data : 2018/9/8
 */
@RestController
@RequestMapping("weixin")
@Slf4j
public class WeixinController
{

    /**
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx54beb2091902d73a&
     * redirect_uri=http://locrock.nat300.top/weixin/auth&
     * response_type=code&
     * scope=snsapi_base&
     * state=STATE#wechat_redirect
     * 若提示“该链接无法访问”，请检查参数是否填写错误，是否拥有scope参数对应的授权作用域权限。
     */

    /**
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx54beb2091902d73a&secret=abaa543c53019db5a9795c790ed65d8f&code=0214KTVG0mqnmj2in3WG0jZZVG04KTVF&grant_type=authorization_code
     */

    /**
     * [openid] openid = oFRZE0U1p3mIC106okwNK-K3yXxI
     */

    private final String appid="wx54beb2091902d73a";
    private final String secret="abaa543c53019db5a9795c790ed65d8f";
    @Resource
    private HttpServletResponse response;

    @Resource
    private HttpServletRequest request;
    public static final String TOKEN = "locrock_zyb_token";

    @RequestMapping("getToken")
    protected void getToken(HttpServletRequest request, HttpServletResponse response){
        try {
            // 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数
            String signature = request.getParameter("signature");// 微信加密签名（token、timestamp、nonce。）
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");// 随机字符串
            PrintWriter out = response.getWriter();
            // 将token、timestamp、nonce三个参数进行字典序排序
            String[] params = new String[] { TOKEN, timestamp, nonce };
            Arrays.sort(params);
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            String clearText = params[0] + params[1] + params[2];
            String algorithm = "SHA-1";
            String sign = new String(
                    Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
            // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            if (signature.equals(sign)) {
                response.getWriter().print(echostr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("auth")
    public void auth(@RequestParam("code")String code){
        log.info ("进入auth方法");

        log.info ("[获取code] code={}",code);

        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+
                "&secret="+secret+
                "&code="+code+"&grant_type=authorization_code";

        RestTemplate restTemplate=new RestTemplate ();

        String response = restTemplate.getForObject (url, String.class);

        log.info ("[返回信息] response={}",response);
    }

}
