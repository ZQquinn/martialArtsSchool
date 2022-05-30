package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.ShopSku;
import com.tencent.wxcloudrun.entity.SkuImg;
import com.tencent.wxcloudrun.service.impl.ShopSkuServiceImpl;
import com.tencent.wxcloudrun.service.impl.SkuImgServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 商城商品sku 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/shopSku")
@Api(tags = "商品")
public class ShopSkuController {

    @Autowired
    private ShopSkuServiceImpl shopSkuService;

    @Autowired
    private SkuImgServiceImpl skuImgService;

    @GetMapping
    @ApiOperation("商品列表")
    public JsonResult list(@RequestParam(required = false) @ApiParam("商品名称") String skuName){
        LambdaQueryWrapper<ShopSku> shopSkuQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(skuName)){
            shopSkuQueryWrapper.like(ShopSku::getTitle,skuName).or().like(ShopSku::getDescription,skuName);
        }
        return JsonResult.success(shopSkuService.list(shopSkuQueryWrapper));
    }

    @GetMapping
    @ApiOperation("商品详情")
    public JsonResult get(@PathVariable Integer id){
        ShopSku shopSku = shopSkuService.getById(id);
        shopSku.setSkuImgs(skuImgService.list(new LambdaQueryWrapper<SkuImg>().eq(SkuImg::getSkuId,shopSku.getSkuId())));
        return JsonResult.success(shopSku);
    }

}
