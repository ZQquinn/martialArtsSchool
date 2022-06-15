package com.tencent.wxcloudrun.controller;

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
import com.tencent.wxcloudrun.exception.BizException;
import com.tencent.wxcloudrun.service.impl.WxPayServiceImpl;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wxPay")
@Api(tags = "微信支付")
public class WxPayV3Controller {

    @Resource
    WxPayV3Bean wxPayV3Bean;

    @Autowired
    private WxPayServiceImpl wxPayService;


    @SneakyThrows
    @PostMapping("/jsApiPay")
    public JsonResult jsApiPay(String openId) {
        return JsonResult.success(wxPayService.jsApiPay(openId));
    }


    @PostMapping("/payNotify")
    public void payNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            wxPayService.payNotify(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows
    @GetMapping("/queryOrder")
    public JsonResult queryOrder(String outTradeNo){
        return JsonResult.success(wxPayService.queryOrder(outTradeNo));
    }

}
