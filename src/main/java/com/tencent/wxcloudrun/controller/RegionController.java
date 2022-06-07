package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.Region;
import com.tencent.wxcloudrun.service.impl.RegionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 省市、国家 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/region")
@Api(tags = "省市、国家区域")
public class RegionController {

    @Autowired
    private RegionServiceImpl regionService;

    @GetMapping("/list")
    @ApiOperation("注册时用到区域")
    private JsonResult list(@ApiParam("1-国内 2-海外") int type,
                            @RequestParam(required = false) @ApiParam("父级区域") String regionParent){
        LambdaQueryWrapper<Region> wrapper = new QueryWrapper<Region>().lambda().eq(Region::getType, type);

        if(StringUtils.isNotBlank(regionParent)){
            wrapper.eq(Region::getRegionParent,regionParent);
        }
        return JsonResult.success(regionService.list(wrapper));
    }

}
