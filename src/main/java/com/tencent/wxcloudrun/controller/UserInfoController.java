package com.tencent.wxcloudrun.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.aop.UserLoginToken;
import com.tencent.wxcloudrun.utils.LocalCache;
import com.tencent.wxcloudrun.vo.UserInfoVo;
import com.tencent.wxcloudrun.common.aop.PassToken;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.*;
import com.tencent.wxcloudrun.service.impl.*;
import com.tencent.wxcloudrun.utils.JwtUtils;
import io.swagger.annotations.*;
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
@RestController
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId",value = "openId",dataTypeClass = String.class),
            @ApiImplicitParam(name = "phone",value = "手机号",dataTypeClass = String.class)
    })
    public JsonResult login( String openId,String phone) {

        UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getOpenId, openId).eq(UserInfo::getPhone,phone));

        return userInfo != null ? JsonResult.success(JwtUtils.createToken(userInfo.getId().toString(), openId)) : JsonResult.error(NO_REGISTER);
    }

    @GetMapping("/allUser")
    @ApiOperation("全部校友")
    @UserLoginToken
    public JsonResult allUser() {
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.lambda().select(UserInfo::getUserName,UserInfo::getAvatarUrl).eq(UserInfo::getStatus,2).eq(UserInfo::getIsFlagCheckMe,1).orderByDesc(UserInfo::getUpdateTime).last("limit 100");
        return JsonResult.success(userInfoService.list(userInfoQueryWrapper));
    }


    @GetMapping("/usersByMajor")
    @ApiOperation("查询校友通过专业")
    @ApiImplicitParam(name = "majorCode",value = "专业编码",dataTypeClass = String.class)
    @UserLoginToken
    public JsonResult usersByMajor(String majorCode) {
        List<UserMajorRelation> userMajorRelations = userMajorRelationService.list(new QueryWrapper<UserMajorRelation>().lambda().eq(UserMajorRelation::getMajorCode, majorCode));
        List<Integer> userIds = userMajorRelations.stream().map(UserMajorRelation::getUserId).collect(Collectors.toList());

        List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>().lambda().select(UserInfo::getUserName,UserInfo::getAvatarUrl).eq(UserInfo::getStatus,1).eq(UserInfo::getIsFlagFindMe,1).in(UserInfo::getId, userIds).orderByDesc(UserInfo::getUpdateTime));
        return JsonResult.success(list);
    }

    @GetMapping("/usersByRegion")
    @ApiOperation("查询校友通过区域")
    @UserLoginToken
    @ApiImplicitParam(name = "regionCode",value = "区域编码",dataTypeClass = String.class)
    public JsonResult usersByRegion(String regionCode) {
        List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>().lambda().select(UserInfo::getUserName,UserInfo::getAvatarUrl).eq(UserInfo::getStatus,1).eq(UserInfo::getIsFlagFindMe,1).in(UserInfo::getRegionCode, regionCode).orderByDesc(UserInfo::getUpdateTime));

        return JsonResult.success(list);
    }

    @GetMapping("/usersByYear")
    @ApiOperation("查询校友通过年份")
    @UserLoginToken
    @ApiImplicitParam(name = "year",value = "高考年份",dataTypeClass = String.class)
    public JsonResult usersByYear(String year) {

        List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>().lambda().select(UserInfo::getUserName,UserInfo::getAvatarUrl).eq(UserInfo::getStatus,1).eq(UserInfo::getIsFlagFindMe,1).eq(UserInfo::getCollegeEntranceTime, year).orderByDesc(UserInfo::getUpdateTime));

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
    @ApiImplicitParam(name = "userId",value = "校友id",dataTypeClass = Integer.class)
    @UserLoginToken
    public JsonResult getAlumnusInfo(Integer userId) {

        UserInfoVo userInfoVo = new UserInfoVo();
        UserInfo userInfo = userInfoService.getById(userId);

        BeanUtil.copyProperties(userInfo, userInfoVo);

        if (userInfo.getIsFlagCheckMe() == 1) {
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
    public JsonResult getUserInfo(){
        Integer userId = LocalCache.getInt("userId");
        UserInfoVo userInfoVo = new UserInfoVo();
        UserInfo userInfo = userInfoService.getById(userId);
        BeanUtil.copyProperties(userInfo,userInfoVo);

        List<UserMajorRelation> userMajorRelations = userMajorRelationService.list(new QueryWrapper<UserMajorRelation>().lambda().eq(UserMajorRelation::getUserId, userId));
        List<Major> majors = majorService.list(new QueryWrapper<Major>().lambda().in(Major::getMajorCode, userMajorRelations.stream().map(UserMajorRelation::getMajorCode).collect(Collectors.toList())));

        userInfoVo.setMajors(majors);
        return JsonResult.success(userInfoVo);
    }

}
