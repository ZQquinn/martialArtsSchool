package com.tencent.wxcloudrun.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wxpay")
@Data
public class WxPayV3Bean {
    private String appId;
    private String keyPath;
    private String certPath;
    private String certP12Path;
    private String platformCertPath;
    private String mchId;
    private String apiKey;
    private String apiKey3;
    private String domain;
    private String serialNo;
}
