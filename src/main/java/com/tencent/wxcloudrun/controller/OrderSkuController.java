package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.service.impl.OrderSkuServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 订单商品 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/orderSku")
@Api(tags = "订单商品")
public class OrderSkuController {

    @Autowired
    private OrderSkuServiceImpl orderSkuService;

}