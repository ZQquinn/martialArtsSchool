package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.AlumniCard;
import com.tencent.wxcloudrun.service.impl.AlumniCardServiceImpl;
import com.tencent.wxcloudrun.utils.JwtUtils;
import com.tencent.wxcloudrun.utils.LocalCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 校友卡 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/alumniCard")
@Api(tags = "校友卡")
public class AlumniCardController {

    @Autowired
    private AlumniCardServiceImpl alumniCardService;

    @PostMapping("/save")
    @ApiModelProperty("注册或更新")
    public JsonResult save(@RequestBody AlumniCard alumniCard,@RequestHeader(value = "token",required = false) String token){
//        Integer userId = JwtUtils.getAudience(token);
        Integer userId = LocalCache.getInt("userId");
        alumniCard.setUserId(userId);

        return JsonResult.success(alumniCardService.saveOrUpdate(alumniCard));
    }

    @PostMapping("/query")
    public JsonResult query(@RequestHeader(value = "token",required = false) String token){
//        Integer userId = JwtUtils.getAudience(token);
        Integer userId = LocalCache.getInt("userId");

        return JsonResult.success(alumniCardService.getById(userId));
    }

}
