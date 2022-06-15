package com.tencent.wxcloudrun.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.http.HttpUtil;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.config.WxMaConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/wx")
@Api(tags = "微信相关接口")
public class WxController {

    @Value("${wxpay.appId}")
    private String appId;


    @PostMapping("/login")
    @ApiOperation("微信登陆")
    public JsonResult login(String code) {

        final WxMaService wxService = WxMaConfiguration.getMaService(appId);

        WxMaJscode2SessionResult session = null;
        try {
            session = wxService.getUserService().getSessionInfo(code);
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return JsonResult.success(session);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return JsonResult.error(null);
    }

    @GetMapping("/getAccessToken")
    @ApiOperation("获取微信access_token")
    public JsonResult getAccessToken() {

        final WxMaService wxService = WxMaConfiguration.getMaService(appId);

        try {
            return JsonResult.success(wxService.getAccessToken(true));
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;

    }


    @GetMapping("/getPhone")
    @ApiOperation("获取微信手机号")
    public JsonResult getPhone(String accessToken, String code) {

        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("access_token", accessToken);
        paramMap.put("code", code);

        String result = HttpUtil.post("https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken, paramMap);
        return JsonResult.success(result);

    }
}
