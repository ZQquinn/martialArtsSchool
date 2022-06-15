package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.aop.UserLoginToken;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.UserMessage;
import com.tencent.wxcloudrun.service.impl.UserMessageServiceImpl;
import com.tencent.wxcloudrun.service.impl.UserPhotoServiceImpl;
import com.tencent.wxcloudrun.utils.JwtUtils;
import com.tencent.wxcloudrun.utils.LocalCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 用户消息 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/userMessage")
@Api(tags = "用户消息")
public class UserMessageController {

    @Autowired
    private UserMessageServiceImpl userMessageService;

    @PostMapping
    @ApiOperation("添加消息")
    public JsonResult insert(@RequestBody UserMessage userMessage){

        return JsonResult.success(userMessageService.save(userMessage));
    }

    @GetMapping
    @ApiOperation("消息列表")
    @UserLoginToken
    public JsonResult<List<UserMessage>> list(){
        Integer userId = LocalCache.getInt("userId");
//        Integer userId = JwtUtils.getAudience(token);
        return JsonResult.success(userMessageService.list(new QueryWrapper<UserMessage>().lambda().eq(UserMessage::getUserId,userId)));
    }

    @GetMapping("/{id}")
    @ApiOperation("消息详情")
    public JsonResult<UserMessage> get(@PathVariable Integer id){
        return JsonResult.success(userMessageService.getById(id));
    }

}