package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ijpay.core.IJPayHttpResponse;
import com.ijpay.core.enums.RequestMethod;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.core.utils.DateTimeZoneUtil;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.enums.WxApiType;
import com.ijpay.wxpay.enums.WxDomain;
import com.ijpay.wxpay.model.v3.Amount;
import com.ijpay.wxpay.model.v3.Payer;
import com.ijpay.wxpay.model.v3.UnifiedOrderModel;
import com.tencent.wxcloudrun.component.WxPayV3Bean;
import com.tencent.wxcloudrun.entity.Order;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import static com.tencent.wxcloudrun.enums.OrderStatus.WAITING_SHIPPED;

@Service
public class WxPayServiceImpl {

    @Resource
    WxPayV3Bean wxPayV3Bean;

    @Autowired
    private OrderServiceImpl orderService;

    public Object jsApiPay(String openId,String outTradeNo) throws Exception {

        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(FileUtil.readUtf8String(wxPayV3Bean.getKeyPath()));
        String timeExpire = DateTimeZoneUtil.dateToTimeZone(System.currentTimeMillis() + 1000 * 60 * 3);
        UnifiedOrderModel unifiedOrderModel = new UnifiedOrderModel()
                .setAppid(wxPayV3Bean.getAppId())
                .setMchid(wxPayV3Bean.getMchId())
                .setDescription("IJPay 让支付触手可及")
                .setOut_trade_no(outTradeNo)
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
                wxPayV3Bean.getSerialNo(),
                null,
                merchantPrivateKey,
                JSONUtil.toJsonStr(unifiedOrderModel)
        );


//        System.out.println(PayKit.getPrivateKey(wxPayV3Bean.getKeyPath()));

        if (response.getStatus() == 200) {
            // 根据证书序列号查询对应的证书来验证签名结果
//            boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
//            if (verifySignature) {
            String body = response.getBody();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            String prepayId = jsonObject.getStr("prepay_id");
            Map<String, String> map = WxPayKit.miniAppPrepayIdCreateSign(wxPayV3Bean.getAppId(), prepayId, "",null);
            return map;
//            }
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
            com.alibaba.fastjson.JSONObject parseObject = JSON.parseObject(plainText);
            //订单业务状态更改 成功
            UpdateWrapper<Order> orderUpdateWrapper = new UpdateWrapper<>();
            orderUpdateWrapper.lambda().eq(Order::getOrderNo,parseObject.get("out_trade_no")).set(Order::getStatus,WAITING_SHIPPED);
            orderService.update(orderUpdateWrapper);
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
                    wxPayV3Bean.getSerialNo(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    JSONUtil.toJsonStr(unifiedOrderModel)

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONUtil.parseObj(response.getBody());


    }

}
