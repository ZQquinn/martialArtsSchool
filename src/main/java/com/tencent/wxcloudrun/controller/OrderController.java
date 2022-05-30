package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/order")
@Api(tags = "订单")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    public JsonResult createOrder() {

        String orderId = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        //创建订单
        //创建订单商品
        //返回prepay_id
        return null;
    }

    public JsonResult closeOrder() {
        return null;
    }

    public JsonResult queryOrder() {
        return null;
    }

    public JsonResult callBackOrder() {
        return null;
    }

}
