package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ijpay.core.IJPayHttpResponse;
import com.ijpay.core.enums.RequestMethod;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.PayKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.core.utils.DateTimeZoneUtil;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.enums.WxApiType;
import com.ijpay.wxpay.enums.WxDomain;
import com.ijpay.wxpay.model.v3.Amount;
import com.ijpay.wxpay.model.v3.Payer;
import com.ijpay.wxpay.model.v3.UnifiedOrderModel;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.component.WxPayV3Bean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

@Service
public class WxPayServiceImpl {

    @Resource
    WxPayV3Bean wxPayV3Bean;

    public Object jsApiPay(String openId) throws Exception {
        String timeExpire = DateTimeZoneUtil.dateToTimeZone(System.currentTimeMillis() + 1000 * 60 * 3);
        UnifiedOrderModel unifiedOrderModel = new UnifiedOrderModel()
                .setAppid(wxPayV3Bean.getAppId())
                .setMchid(wxPayV3Bean.getMchId())
                .setDescription("IJPay 让支付触手可及")
                .setOut_trade_no(PayKit.generateStr())
                .setTime_expire(timeExpire)
                .setAttach("微信系开发脚手架 https://gitee.com/javen205/TNWX")
                .setNotify_url(wxPayV3Bean.getDomain().concat("/v3/payNotify"))
                .setAmount(new Amount().setTotal(1))
                .setPayer(new Payer().setOpenid(openId));

        IJPayHttpResponse response = WxPayApi.v3(
                RequestMethod.POST,
                WxDomain.CHINA.toString(),
                WxApiType.JS_API_PAY.toString(),
                wxPayV3Bean.getMchId(),
                getSerialNumber(),
                null,
                wxPayV3Bean.getKeyPath(),
                JSONUtil.toJsonStr(unifiedOrderModel)
        );

        if (response.getStatus() == 200) {
            // 根据证书序列号查询对应的证书来验证签名结果
            boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
            if (verifySignature) {
                String body = response.getBody();
                JSONObject jsonObject = JSONUtil.parseObj(body);
                String prepayId = jsonObject.getStr("prepay_id");
                Map<String, String> map = WxPayKit.jsApiCreateSign(wxPayV3Bean.getAppId(), prepayId, wxPayV3Bean.getKeyPath());
                return map;
            }
        }
        return response;
    }


    public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> map = new HashMap<>(12);
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        String nonce = request.getHeader("Wechatpay-Nonce");
        String serialNo = request.getHeader("Wechatpay-Serial");
        String signature = request.getHeader("Wechatpay-Signature");

        String result = HttpKit.readData(request);

        // 需要通过证书序列号查找对应的证书，verifyNotify 中有验证证书的序列号
        String plainText = WxPayKit.verifyNotify(serialNo, result, signature, nonce, timestamp,
                wxPayV3Bean.getApiKey3(), wxPayV3Bean.getPlatformCertPath());


        if (StrUtil.isNotEmpty(plainText)) {
            response.setStatus(200);
            map.put("code", "SUCCESS");
            map.put("message", "SUCCESS");



            //订单业务状态更改




        } else {
            response.setStatus(500);
            map.put("code", "ERROR");
            map.put("message", "签名错误");
        }
        response.setHeader("Content-type", ContentType.JSON.toString());
        response.getOutputStream().write(JSONUtil.toJsonStr(map).getBytes(StandardCharsets.UTF_8));
        response.flushBuffer();
    }

    public JSONObject queryOrder(String outTradeNo) {

        UnifiedOrderModel unifiedOrderModel = new UnifiedOrderModel()
                .setMchid(wxPayV3Bean.getMchId());

        IJPayHttpResponse response = null;
        try {
            response = WxPayApi.v3(
                    RequestMethod.GET,
                    WxDomain.CHINA.toString(),
                    String.format(WxApiType.ORDER_QUERY_BY_NO.toString(), outTradeNo),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    JSONUtil.toJsonStr(unifiedOrderModel)

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONUtil.parseObj(response.getBody());


    }


    private String getSerialNumber() {
        // 获取证书序列号
        X509Certificate certificate = PayKit.getCertificate(FileUtil.getInputStream(wxPayV3Bean.getCertPath()));
        String serialNo = certificate.getSerialNumber().toString(16).toUpperCase();

//            System.out.println("输出证书信息:\n" + certificate.toString());
//            // 输出关键信息，截取部分并进行标记
//            System.out.println("证书序列号:" + certificate.getSerialNumber().toString(16));
//            System.out.println("版本号:" + certificate.getVersion());
//            System.out.println("签发者：" + certificate.getIssuerDN());
//            System.out.println("有效起始日期：" + certificate.getNotBefore());
//            System.out.println("有效终止日期：" + certificate.getNotAfter());
//            System.out.println("主体名：" + certificate.getSubjectDN());
//            System.out.println("签名算法：" + certificate.getSigAlgName());
//            System.out.println("签名：" + certificate.getSignature().toString());
        return serialNo;
    }
}
