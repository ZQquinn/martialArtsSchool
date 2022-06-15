package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.dto.OrderQueryDto;
import com.tencent.wxcloudrun.dto.OrderRequestDto;
import com.tencent.wxcloudrun.entity.Order;
import com.tencent.wxcloudrun.service.impl.OrderServiceImpl;
import com.tencent.wxcloudrun.service.impl.WxPayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/list")
    @ApiOperation("查询个人订单列表")
    public JsonResult<List<Order>> orderList(OrderQueryDto OrderQueryDto) {
        return JsonResult.success(orderService.orderList(OrderQueryDto));
    }

    @PostMapping("/page")
    @ApiOperation("订单分页")
    public JsonResult<IPage<Order>> orderPage(OrderQueryDto OrderQueryDto) {
        return JsonResult.success(orderService.getOrderPage(OrderQueryDto));
    }

    @PostMapping("/orderDetail")
    @ApiOperation("订单详情")
    public JsonResult orderDetail(String outTradeNo){
        return JsonResult.success(orderService.getOrderDetail(outTradeNo));
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

    @GetMapping("/countFreight")
    @ApiOperation("运费计算")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "区域编码"),
            @ApiImplicitParam(name = "actualPrice",value = "商品总价")
    })
    public JsonResult countFreight(String code,String actualPrice){
        return  JsonResult.success(orderService.countFreight(code,new BigDecimal(actualPrice)));
    }

}
