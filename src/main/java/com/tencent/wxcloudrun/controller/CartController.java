package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.common.aop.UserLoginToken;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.Cart;
import com.tencent.wxcloudrun.entity.ShopSku;
import com.tencent.wxcloudrun.service.impl.CartServiceImpl;
import com.tencent.wxcloudrun.service.impl.ShopSkuServiceImpl;
import com.tencent.wxcloudrun.utils.JwtUtils;
import com.tencent.wxcloudrun.utils.LocalCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/cart")
@Api(tags = "购物车")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private ShopSkuServiceImpl shopSkuService;

    @PostMapping
    @ApiOperation("添加购物车")
    @UserLoginToken
    public JsonResult add( @RequestBody Cart cart) {
//        Integer userId = JwtUtils.getAudience(token);
        Integer userId = LocalCache.getInt("userId");
        cart.setUserId(userId);
        return JsonResult.success(cartService.save(cart));
    }

    @GetMapping
    @ApiOperation("购物车列表")
    @UserLoginToken
    public JsonResult list() {
//        Integer userId = JwtUtils.getAudience(token);
        Integer userId = LocalCache.getInt("userId");
        List<Cart> carts = cartService.list(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId));
        JSONArray resultArray = new JSONArray();
        carts.forEach(cart -> {
            ShopSku shopSku = shopSkuService.getById(cart.getSkuId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("shopSku", shopSku);
            jsonObject.put("num", cart.getNum());
            resultArray.add(jsonObject);
        });

        return JsonResult.success(resultArray);
    }

    @PostMapping("/delete")
    @ApiOperation("删除购物车商品")
    @UserLoginToken
    public JsonResult delBatch(List<Integer> skuIds) {
        Integer userId = LocalCache.getInt("userId");
//        Integer userId = JwtUtils.getAudience(token);
        return JsonResult.success(cartService.remove(new LambdaQueryWrapper<Cart>().in(Cart::getSkuId, skuIds).eq(Cart::getUserId, userId)));
    }
}
