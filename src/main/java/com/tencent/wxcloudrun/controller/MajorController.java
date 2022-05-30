package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.service.impl.MajorServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 专业 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/major")
@Api(tags = "专业")
public class MajorController {

    @Autowired
    private MajorServiceImpl majorService;

    @GetMapping("/list")
    public JsonResult getMajors(){
        return JsonResult.success(majorService.list());
    }
}
