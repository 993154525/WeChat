package com.st.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * AllClose
 * @author shaotian
 * @create 2020-03-11 14:25
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    @Value("${wechat.mpAppId}")
    private String mpAppId;

    @Value("${wechat.mpAppSecret}")
    private String mpAppSecret;

    /**
     * 开放平台id
     */
    private String openAppId = "oTgZpwR5ALz2o5NYQYbPa8qJvMKQ";

    private String mpAppId2 = "wxd898fcb01713c658";

    /**
     * 开放平台密钥
     */
    private String openAppSecret = "098F6BCD4621D373CADE4E832627B4F6";

    /**
     * 商户号
     */
    private String mchId = "1483469312";

    /**
     * 商户密钥
     */
    private String mchKey = "098F6BCD4621D373CADE4E832627B4F6";

    /**
     * 商户证书路径
     */
    private String keyPath = "D:\\Java\\offer\\springboot微信点餐系统\\copy-422\\doc\\h5.p12";

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl = "http://sellst.natapp1.cc/sell/pay/notify";

    /**
     * 微信模版id
     */
    private Map<String, String> templateId;
}


