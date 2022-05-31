package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.dto.OrderQueryDto;
import com.tencent.wxcloudrun.dto.OrderRequestDto;
import com.tencent.wxcloudrun.service.impl.OrderServiceImpl;
import com.tencent.wxcloudrun.service.impl.WxPayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private WxPayServiceImpl wxPayService;

    @PostMapping
    @ApiOperation("创建订单")
    public JsonResult createOrder(OrderRequestDto orderRequestDto) {

        //创建订单
        //创建订单商品
        //返回prepay_id
        return JsonResult.success(orderService.createOrder(orderRequestDto));
    }

    public JsonResult closeOrder() {
        return null;
    }

    @PostMapping("/orderList")
    @ApiOperation("查询个人订单列表")
    public JsonResult orderList(OrderQueryDto OrderQueryDto) {
        return JsonResult.success(orderService.orderList(OrderQueryDto));
    }

    @PostMapping("/orderPage")
    @ApiOperation("订单分页")
    public JsonResult orderPage(OrderQueryDto OrderQueryDto) {
        return JsonResult.success(orderService.getOrderPage(OrderQueryDto));
    }

    @RequestMapping(value = "/payNotify", method = {org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public void payNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            wxPayService.payNotify(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
