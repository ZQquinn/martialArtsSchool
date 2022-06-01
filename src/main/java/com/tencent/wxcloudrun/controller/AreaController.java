package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.Area;
import com.tencent.wxcloudrun.service.impl.AreaServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-06-01
 */
@Controller
@RequestMapping("/area")
@Api(tags = "收货地址")
public class AreaController {

    @Autowired
    private AreaServiceImpl areaService;

    @GetMapping("/list")
    @ApiOperation("收货地址查询列表")
    @ApiImplicitParam(name = "code", value = "地区编码", required = false)
    public JsonResult list(@RequestParam(required = false) String code) {

        QueryWrapper<Area> areaQueryWrapper = null;
        if (StringUtils.isNotBlank(code)) {
            areaQueryWrapper = new QueryWrapper<>();
        }

        List<Area> list = areaService.list(areaQueryWrapper.lambda()
                .eq(Area::getCodeProv, code).or().eq(Area::getCodeCity, code).or().eq(Area::getCodeCoun, code)
                .orderByAsc(Area::getCodeProv, Area::getCodeCity, Area::getCodeCoun));

        return JsonResult.success(list);
    }

}
