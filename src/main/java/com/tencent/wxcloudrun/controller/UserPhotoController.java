package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.aop.UserLoginToken;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.dto.PhotoDto;
import com.tencent.wxcloudrun.entity.UserPhoto;
import com.tencent.wxcloudrun.service.impl.UserPhotoServiceImpl;
import com.tencent.wxcloudrun.utils.JwtUtils;
import com.tencent.wxcloudrun.utils.LocalCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 用户拾忆照片 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/userPhoto")
@Api(tags = "用户拾忆照片")
public class UserPhotoController {

    @Autowired
    private UserPhotoServiceImpl userPhotoService;

    @PostMapping("/addBatch")
    @ApiOperation("添加拾忆照片")
    @UserLoginToken
    public JsonResult add(@RequestBody PhotoDto photoDto) {

        Integer userId = LocalCache.getInt("userId");
        photoDto.getPhotos().forEach(userPhoto -> {
            userPhoto.setUserId(userId);
            userPhoto.setAlbumId(photoDto.getAlbumId());
        });
        return JsonResult.success(userPhotoService.saveBatch(photoDto.getPhotos()));
    }


    @GetMapping("/uploadList")
    @ApiOperation("拾忆照片上传列表")
    public JsonResult<List<UserPhoto>> uploadList(@ApiParam("相册id") int albumId) {
        Integer userId = LocalCache.getInt("userId");
        return JsonResult.success(userPhotoService.list(new QueryWrapper<UserPhoto>().lambda().eq(UserPhoto::getAlbumId,albumId).eq(UserPhoto::getStatus,1).eq(UserPhoto::getUserId,userId)));
    }

    @GetMapping("/reviewList")
    @ApiOperation("拾忆照片审核列表")
    @UserLoginToken
    public JsonResult<List<UserPhoto>> reviewList(@ApiParam("相册id") int albumId) {
        Integer userId = LocalCache.getInt("userId");
        return JsonResult.success(userPhotoService.list(new QueryWrapper<UserPhoto>().lambda().eq(UserPhoto::getStatus,2).eq(UserPhoto::getAlbumId,albumId).eq(UserPhoto::getUserId, userId)));
    }


    @GetMapping("/delPhoto")
    @ApiOperation("批量删除照片")
    public JsonResult delPhoto(@RequestBody List<Integer> ids){
        return JsonResult.success(userPhotoService.removeBatchByIds(ids));
    }

}
