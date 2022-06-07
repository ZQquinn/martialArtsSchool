package com.tencent.wxcloudrun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.dto.AlumniCardDto;
import com.tencent.wxcloudrun.dto.PhotoDto;
import com.tencent.wxcloudrun.dto.ShopSkuDto;
import com.tencent.wxcloudrun.dto.UserInfoDto;
import com.tencent.wxcloudrun.entity.*;
import com.tencent.wxcloudrun.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@Api(tags = "后台管理员")
public class AdminController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private AlumniCardServiceImpl alumniCardService;

    @Autowired
    private UserMajorRelationServiceImpl userMajorRelationService;

    @Autowired
    private ShopSkuServiceImpl shopSkuService;

    @Autowired
    private SkuImgServiceImpl skuImgService;

    @Autowired
    private SkuAttributesServiceImpl skuAttributesService;

    @Autowired
    private AlbumServiceImpl albumService;

    @Autowired
    private UserPhotoServiceImpl userPhotoService;

    @PostMapping("/user/page")
    @ApiOperation("校友分页列表")
    public JsonResult userPage(@RequestBody UserInfoDto userInfoDto) {

        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 校友状态 已审核 待审核
        if (userInfoDto.getStatus() != 0) {
            lambdaQueryWrapper.eq(UserInfo::getStatus, userInfoDto.getStatus());
        }

        //校友名称
        if (StringUtils.isNotBlank(userInfoDto.getUserName())) {
            lambdaQueryWrapper.like(UserInfo::getUserName, userInfoDto.getUserName());
        }

        //在校时间
        if (StringUtils.isNotBlank(userInfoDto.getStartTime())) {
            lambdaQueryWrapper.between(UserInfo::getSchoolAt, userInfoDto.getStartTime(), userInfoDto.getEndTime());
        }

        //高考年份
        if (StringUtils.isNotBlank(userInfoDto.getCollegeEntranceTime())) {
            lambdaQueryWrapper.eq(UserInfo::getCollegeEntranceTime, userInfoDto.getCollegeEntranceTime());
        }

        //区域
        if (StringUtils.isNotBlank(userInfoDto.getRegion())) {
            lambdaQueryWrapper.eq(UserInfo::getRegionCode, userInfoDto.getRegion());
        }

        //专业
        if (userInfoDto.getMajorCodes().isEmpty()) {
            List<UserMajorRelation> relations = userMajorRelationService.list(new QueryWrapper<UserMajorRelation>().lambda().in(UserMajorRelation::getMajorCode, userInfoDto.getMajorCodes()));
            lambdaQueryWrapper.in(UserInfo::getId, relations.stream().map(UserMajorRelation::getUserId).collect(Collectors.toList()));
        }


        return JsonResult.success(userInfoService.page(userInfoDto.getPagePlus(), lambdaQueryWrapper));
    }

    @PostMapping("/user/save")
    @ApiOperation("保存校友")
    public JsonResult addUser(@RequestBody UserInfo userInfo) {
        return JsonResult.success(userInfoService.saveOrUpdate(userInfo));
    }

    @PostMapping("/user/delete")
    @ApiOperation("删除校友")
    public JsonResult delUser(List<Integer> ids) {
        return JsonResult.success(userInfoService.removeBatchByIds(ids));
    }


    @GetMapping("/user/{id}")
    @ApiOperation("校友详情")
    public JsonResult userDetail(@PathVariable int id) {
        return JsonResult.success(userInfoService.getById(id));
    }

    //校友卡后台功能
    @PostMapping("/card/page")
    @ApiOperation("校友分页列表")
    public JsonResult cardPage(@RequestBody AlumniCardDto alumniCardDto) {

        LambdaQueryWrapper<AlumniCard> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(AlumniCard::getStatus, alumniCardDto.getStatus());

        if (StringUtils.isNotBlank(alumniCardDto.getUserName())) {
            List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>().
                    lambda().select(UserInfo::getId).like(UserInfo::getUserName, alumniCardDto.getUserName()));

            lambdaQueryWrapper.in(AlumniCard::getUserId, list.stream().map(UserInfo::getId).collect(Collectors.toList()));
        }

        return JsonResult.success(alumniCardService.page(alumniCardDto.getPagePlus(), lambdaQueryWrapper));
    }

    @PostMapping("/card/delete")
    @ApiOperation("删除校友卡")
    public JsonResult delCard(List<Integer> ids) {
        return JsonResult.success(alumniCardService.removeBatchByIds(ids));
    }

    @PostMapping("/card/save")
    @ApiOperation("保存、更新校友卡")
    public JsonResult saveCard(@RequestBody AlumniCard alumniCard) {
        return JsonResult.success(alumniCardService.saveOrUpdate(alumniCard));
    }

    @PostMapping("/sku/page")
    @ApiOperation("商品列表页")
    public JsonResult skuPage(@RequestBody ShopSkuDto shopSkuDto) {

        QueryWrapper<ShopSku> shopSkuQueryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(shopSkuDto.getTitle())) {
            shopSkuQueryWrapper.lambda().like(ShopSku::getTitle, shopSkuDto.getTitle());
        }

        if (null != shopSkuDto.getStatus()) {
            shopSkuQueryWrapper.lambda().eq(ShopSku::getStatus, shopSkuDto.getStatus());
        }

        return JsonResult.success(shopSkuService.page(shopSkuDto.getPagePlus(), shopSkuQueryWrapper));
    }


    @GetMapping("/sku/detail/{skuId}")
    @ApiOperation("商品详情")
    public JsonResult skuDetail(@PathVariable int skuId) {
        ShopSku shopSku = shopSkuService.getById(skuId);
        shopSku.setSkuImgs(skuImgService.list(new QueryWrapper<SkuImg>().lambda().eq(SkuImg::getSkuId,skuId)));
        shopSku.setSkuAttributes(skuAttributesService.list(new QueryWrapper<SkuAttributes>().lambda().eq(SkuAttributes::getSkuId,skuId)));
        return JsonResult.success(shopSku);
    }

    @PostMapping("/sku/add")
    @ApiOperation("添加商品")
    @Transactional
    public JsonResult skuAdd(@RequestBody ShopSku shopSku) {

        shopSkuService.save(shopSku);
        shopSku.getSkuImgs().forEach(skuImg -> skuImg.setSkuId(shopSku.getSkuId()));
        shopSku.getSkuAttributes().forEach(skuAttributes -> skuAttributes.setSkuId(shopSku.getSkuId()));

        skuAttributesService.saveBatch(shopSku.getSkuAttributes());
        skuImgService.saveBatch(shopSku.getSkuImgs());
        return JsonResult.success();
    }

    @PostMapping("/sku/update")
    @ApiOperation("更新商品")
    @Transactional
    public JsonResult skuUpdate(@RequestBody ShopSku shopSku) {

        shopSkuService.updateById(shopSku);

        skuAttributesService.remove(new QueryWrapper<SkuAttributes>().lambda().eq(SkuAttributes::getSkuId, shopSku.getSkuId()));

        skuImgService.remove(new QueryWrapper<SkuImg>().lambda().eq(SkuImg::getSkuId, shopSku.getSkuId()));

        skuAttributesService.saveBatch(shopSku.getSkuAttributes());
        skuImgService.saveBatch(shopSku.getSkuImgs());
        return JsonResult.success();
    }

    @PostMapping("/album/add")
    @ApiOperation("创建相册")
    public JsonResult albumAdd(@RequestBody Album album){

        return JsonResult.success(albumService.save(album));
    }

    @GetMapping("/album/list")
    @ApiOperation("相册列表")
    public JsonResult albumList( ){
        return JsonResult.success(albumService.list());
    }


    @PostMapping("/album/uploadPhoto")
    @ApiOperation("上传照片进相册")
    public JsonResult uploadPhotoInAlbum(@RequestBody List<UserPhoto> photos){
        return JsonResult.success(userPhotoService.saveBatch(photos));
    }


    @PostMapping("/album/deletePhoto")
    @ApiOperation("删除照片")
    public JsonResult deletePhoto(@RequestBody List<Integer> ids){
        return JsonResult.success(userPhotoService.removeBatchByIds(ids));
    }


//    public JsonResult





}
