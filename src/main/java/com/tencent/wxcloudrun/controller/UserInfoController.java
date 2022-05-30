package com.tencent.wxcloudrun.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.vo.UserInfoVo;
import com.tencent.wxcloudrun.common.aop.PassToken;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.*;
import com.tencent.wxcloudrun.service.impl.*;
import com.tencent.wxcloudrun.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tencent.wxcloudrun.exception.CommonEnum.NO_ALLOW_CHECK;
import static com.tencent.wxcloudrun.exception.CommonEnum.NO_REGISTER;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/userInfo")
@Api(tags = "用户信息")
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private UserMajorRelationServiceImpl userMajorRelationService;

    @Autowired
    private RegionServiceImpl regionService;

    @Autowired
    private MajorServiceImpl majorService;


    @PostMapping("/registerUser")
    @ApiOperation("注册用户")
    public JsonResult registerUser(@RequestBody UserInfo userInfo) {
        userInfoService.saveOrUpdate(userInfo);
        if (!userInfo.getMajorCodes().isEmpty()) {
            List<UserMajorRelation> userMajorRelationList = new ArrayList<>();

            userInfo.getMajorCodes().forEach(majorCode -> {
                UserMajorRelation userMajorRelation = new UserMajorRelation();
                userMajorRelation.setUserId(userInfo.getId());
                userMajorRelation.setMajorCode(majorCode);
                userMajorRelation.setOpenId(userInfo.getOpenId());
                userMajorRelationList.add(userMajorRelation);
            });
            userMajorRelationService.saveBatch(userMajorRelationList);
        }
        return JsonResult.success();
    }

    @GetMapping("/login")
    @ApiOperation("登陆")
    @PassToken
    public JsonResult login(@RequestParam("微信openId") String openId) {

        UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getOpenId, openId));

        return userInfo != null ? JsonResult.success(JwtUtils.createToken(userInfo.getId().toString(), openId)) : JsonResult.error(NO_REGISTER);
    }

    @GetMapping("/allUser")
    @ApiOperation("全部校友")
    public JsonResult allUser() {
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.lambda().select(UserInfo::getUserName,UserInfo::getAvatarUrl).eq(UserInfo::getStatus,1).eq(UserInfo::isFlagFindMe,true).orderByDesc(UserInfo::getUpdateTime).last("limit 100");
        return JsonResult.success(userInfoService.listObjs(userInfoQueryWrapper));
    }


    @GetMapping("/usersByMajor")
    @ApiOperation("查询校友通过专业")
    public JsonResult usersByMajor(@ApiParam("专业码") String majorCode) {
        List<UserMajorRelation> userMajorRelations = userMajorRelationService.list(new QueryWrapper<UserMajorRelation>().lambda().eq(UserMajorRelation::getMajorCode, majorCode));
        List<Integer> userIds = userMajorRelations.stream().map(UserMajorRelation::getUserId).collect(Collectors.toList());

        List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>().lambda().select(UserInfo::getUserName,UserInfo::getAvatarUrl).eq(UserInfo::getStatus,1).eq(UserInfo::isFlagFindMe,true).in(UserInfo::getId, userIds).orderByDesc(UserInfo::getUpdateTime));
        return JsonResult.success(list);
    }

    @GetMapping("/usersByMajor")
    @ApiOperation("查询校友通过区域")
    public JsonResult usersByRegion(@ApiParam("区域编码") String regionCode) {
        List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>().lambda().select(UserInfo::getUserName,UserInfo::getAvatarUrl).eq(UserInfo::getStatus,1).eq(UserInfo::isFlagFindMe,true).in(UserInfo::getRegionCode, regionCode).orderByDesc(UserInfo::getUpdateTime));

        return JsonResult.success(list);
    }

    @GetMapping("/usersByYear")
    @ApiOperation("查询校友通过年份")
    public JsonResult usersByYear(@ApiParam("高考年份") String year) {

        List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>().lambda().select(UserInfo::getUserName,UserInfo::getAvatarUrl).eq(UserInfo::getStatus,1).eq(UserInfo::isFlagFindMe,true).eq(UserInfo::getCollegeEntranceTime, year).orderByDesc(UserInfo::getUpdateTime));

        return JsonResult.success(list);
    }

    @GetMapping("/yearList")
    @ApiOperation("同级年份列表")
    public JsonResult yearList() {
        List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>().lambda().groupBy(UserInfo::getCollegeEntranceTime).orderByDesc(UserInfo::getCollegeEntranceTime));
        return JsonResult.success(list);
    }

    @GetMapping("/getAlumnusInfo")
    @ApiOperation("观看校友信息")
    public JsonResult getAlumnusInfo(@ApiParam("校友id") Integer userId) {

        UserInfoVo userInfoVo = new UserInfoVo();
        UserInfo userInfo = userInfoService.getById(userId);

        BeanUtil.copyProperties(userInfo, userInfoVo);

        if (userInfo.isFlagCheckMe()) {
            Region region = regionService.getOne(new QueryWrapper<Region>().lambda().eq(Region::getCode, userInfo.getRegionCode()));
            List<UserMajorRelation> userMajorRelations = userMajorRelationService.list(new QueryWrapper<UserMajorRelation>().lambda().eq(UserMajorRelation::getUserId, userId));
            List<Major> majors = majorService.list(new QueryWrapper<Major>().lambda().in(Major::getMajorCode, userMajorRelations.stream().map(UserMajorRelation::getMajorCode).collect(Collectors.toList())));

            userInfoVo.setRegionEntity(region);
            userInfoVo.setMajors(majors);
            JsonResult.success(userInfoVo);
        }

        return new JsonResult(NO_ALLOW_CHECK,userInfoVo);

    }

    @PostMapping("/updateUser")
    @ApiOperation("更新个人信息")
    public JsonResult updateUser(@RequestBody UserInfo userInfo){
        return JsonResult.success(userInfoService.updateById(userInfo));
    }

    @GetMapping("/getUserInfo")
    @ApiOperation("查询个人信息")
    public JsonResult getUserInfo(@ApiParam("用户id") Integer userId){

        UserInfoVo userInfoVo = new UserInfoVo();
        UserInfo userInfo = userInfoService.getById(userId);
        BeanUtil.copyProperties(userInfo,userInfoVo);

        List<UserMajorRelation> userMajorRelations = userMajorRelationService.list(new QueryWrapper<UserMajorRelation>().lambda().eq(UserMajorRelation::getUserId, userId));
        List<Major> majors = majorService.list(new QueryWrapper<Major>().lambda().in(Major::getMajorCode, userMajorRelations.stream().map(UserMajorRelation::getMajorCode).collect(Collectors.toList())));

        userInfoVo.setMajors(majors);
        return JsonResult.success(userInfoVo);
    }


//    public JsonResult


}