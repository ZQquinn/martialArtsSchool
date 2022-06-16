package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.aop.UserLoginToken;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.Album;
import com.tencent.wxcloudrun.entity.UserPhoto;
import com.tencent.wxcloudrun.service.impl.AlbumServiceImpl;
import com.tencent.wxcloudrun.service.impl.UserPhotoServiceImpl;
import com.tencent.wxcloudrun.utils.LocalCache;
import com.tencent.wxcloudrun.vo.AlbumVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 相册 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-06-07
 */
@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumServiceImpl albumService;

    @Autowired
    private UserPhotoServiceImpl userPhotoService;

    @GetMapping("/list")
    @ApiOperation("相册列表")
    public JsonResult<List<AlbumVo>> list(){

        List<AlbumVo> albumVos = new ArrayList<>();
        List<Album> list = albumService.list();

        list.forEach(album -> {
            AlbumVo albumVo = new AlbumVo();

            long count = userPhotoService.count(new QueryWrapper<UserPhoto>().lambda().eq(UserPhoto::getAlbumId, album.getId()).eq(UserPhoto::getStatus, 1));
            albumVo.setAlbum(album);
            albumVo.setPhotoNum(count);
            albumVos.add(albumVo);
        });

        return JsonResult.success(albumVos);

    }


}
