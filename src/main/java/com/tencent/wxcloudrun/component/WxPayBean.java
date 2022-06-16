package com.tencent.wxcloudrun.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>微信配置 Bean</p>
 *
 * @author Javen
 */
@Component
@Data
@ConfigurationProperties(prefix = "wxpay2")
public class WxPayBean {
    private String appId;
    private String appSecret;
    private String mchId;
    private String partnerKey ="-----BEGIN PRIVATE KEY-----\n" +
            "  MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDWO7H9EdnNnXlt\n" +
            "  Dc7YD+jSJQTTRKEwJqGYKaGa04BrTFjBlF7KYw+OiKPuoLJ1GWyrdUwTbdGP/6Hj\n" +
            "  1FhOlu0gypTK8D6CYSWDXqbcWPJc1tDXsPdkffPqwZbTJtzbOTWAqwVet3XS4y/4\n" +
            "  TNBsEKDD9Bn7k+MWKDwPe45UKtzVaquj7W0djxr9ix/SKeiXf+BXAwEB1kEyJzVt\n" +
            "  EItMUHh6TiEC2Ee1gMcg7xCtvkltnlYIBw4zga2FlrMycxk5wplFZ+FIL0Bcqzki\n" +
            "  GDHLIcHhQpaFwMMDXui7YlJtdMvbx0LK3W9vQr0fNR3V4yC85xbh/pPZKd7/ZMG1\n" +
            "  hlroozYrAgMBAAECggEAR7qvCpPKgCzEtj3UTsVs58ebSP2Glq+olWEDCerc6ZAj\n" +
            "  e5Uo88ydLdUURlapyclceEAK28p+Sx+FXdoAQzgHZ15M2XH9C/xo6gZsCgUCCRGn\n" +
            "  1UAcZRpC7AwrasFtWz1Za55XavI0UgrdSd62PiiHG3UEqhZHB2krSufMrOO5Ev9K\n" +
            "  VhD60n89pU8r34bcUMjjeWUvPEXIvNxmg14effIoph8Ev5XX8Y+jqUg4yz65yEC7\n" +
            "  z59ieYBnNCEdioHSP/YsqMSfXazEYCxzahVWAygbZ6nt6foDYqkMG320o7byJb4j\n" +
            "  Rf1WcFMpuxL9QNWxZOXd8CRNHsRpMb32rXekB+STQQKBgQDyFwFUUcBHIcLqZYEc\n" +
            "  Rrgh5SmOt0/pFdtCbYy60Opp4DIBZASYk6is85ilHyDSOf12n5d1N6jXXJ3Pg8I3\n" +
            "  9589QZ6QXvtJLUniASNj/TpQVr+lPMqgeu+6g7fd1bz9wvqx3YzGAUlA5Mo1IMKT\n" +
            "  XDOB8gU5byaDNjoAI6hZ6cmL4QKBgQDiiu9sUscpNHNGFn43hiQPAsmVjctLKi2l\n" +
            "  jtffscrZdRh8LRUXrvfVMYb5wXa1empOqwbLpDWB+rjd8mNPxE8aFmombe6nXOq+\n" +
            "  Gn7yaIgb+CN300IRid0+8Oj/nlZ46TsnTtE7/T6z1w54hg2XZHd9NRuCiONtcn57\n" +
            "  e8pRCKSjiwKBgAIyqUVttJQwv0dHgqOSFMvh+72a9nPESghRXEy55tbtPSb8FHqa\n" +
            "  JJblY0ZkgPTHqABytHpQ9gx2f8xJgvLlIaH5vYnMAvYIHjqaDgszWlsuXnEOmzxX\n" +
            "  wWNtHLc74IphQctpmoSVsGeRbDCNSpbx6FnOq7N4yd25GU+1AytFzxmhAoGBAJP7\n" +
            "  Ya6G3CYkZvBvnP80FrXfgkK5HwT4hlzRVF6BSxqFjPsAj/iqiKWw9pSLNboSPwMo\n" +
            "  YQYBPBqt5rjEru7aMzm9TpXP1cdpwMVxlV9PvSlYDAMn8s52GhoVUH45tg7Be6yP\n" +
            "  pcnkAWOEpaVBG/u62vrlCBVaygUid1C+akhItxNFAoGBAK5PzaZ2cn3Cmx2WAdSh\n" +
            "  75fsEDwHoAoXuxm62TYhxWiroU6eP4UYGKvdtqZIlpck5RJqZx6i7lMtbTgq+VmJ\n" +
            "  jm1l83vUd1YdAPdF4/n22w4yPF6a8lUh7oypHvNy3MmJLdYo65eeBmeyE+E20wdy\n" +
            "  HM5k7oIWf3iAksqbPiTfMqDR\n" +
            "  -----END PRIVATE KEY-----";
    private String certPath;
    private String domain;

}