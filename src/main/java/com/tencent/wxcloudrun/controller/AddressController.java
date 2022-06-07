package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.aop.UserLoginToken;
import com.tencent.wxcloudrun.common.entity.JsonResult;
import com.tencent.wxcloudrun.entity.Address;
import com.tencent.wxcloudrun.exception.BizException;
import com.tencent.wxcloudrun.mapper.AddressMapper;
import com.tencent.wxcloudrun.service.impl.AddressServiceImpl;
import com.tencent.wxcloudrun.utils.LocalCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 地址 前端控制器
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/address")
@Api(tags = "用户地址管理")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private AddressMapper addressMapper;

    @PostMapping("/add")
    @ApiOperation("添加地址")
    @UserLoginToken
    public JsonResult add(@RequestBody Address address) {
//        Integer userId = JwtUtils.getAudience(token);
        Integer userId = LocalCache.getInt("userId");
        address.setUserId(userId);
        if (address.getDefaultAddress() == 1 ) {
            boolean exists = addressMapper.exists(new QueryWrapper<Address>().lambda().eq(Address::getUserId, userId).eq(Address::getDefaultAddress, true));
            if (exists) {
                throw new BizException("已经存在默认地址");
            }
        }
        return JsonResult.success(addressService.save(address));
    }

    @GetMapping("/list")
    @ApiOperation("地址列表")
    @UserLoginToken
    public JsonResult list() {
//        Integer userId =JwtUtils.getAudience(token);
        Integer userId = LocalCache.getInt("userId");
        return JsonResult.success(addressService.list(new QueryWrapper<Address>().lambda().eq(Address::getUserId, userId).orderByDesc(Address::getDefaultAddress)));
    }

    @PostMapping("/update")
    @ApiOperation("更新地址")
    @UserLoginToken
    public JsonResult update(@RequestBody Address address) {
//        Integer userId = JwtUtils.getAudience(token);
        Integer userId = LocalCache.getInt("userId");
        address.setUserId(userId);
        if (address.getDefaultAddress() == 1) {
            boolean exists = addressMapper.exists(new QueryWrapper<Address>().lambda().eq(Address::getUserId, userId).eq(Address::getDefaultAddress, 1));
            if (exists) {
                throw new BizException("已经存在默认地址");
            }
        }

        return JsonResult.success(addressService.updateById(address));
    }

    @PostMapping("/delete")
    @ApiOperation("删除地址")
    public JsonResult delete(@RequestBody Integer id) {

        return JsonResult.success(addressService.removeById(id));
    }

    @GetMapping("/detail")
    @ApiOperation("地址详情")
    public JsonResult query(Integer id) {
        return JsonResult.success(addressService.getById(id));
    }


    @GetMapping("/default")
    @ApiOperation("查询默认地址")
    @UserLoginToken
    public JsonResult queryDefault() {
//        Integer userId = JwtUtils.getAudience(token);
        Integer userId = LocalCache.getInt("userId");
        return JsonResult.success(addressService.getOne(new LambdaQueryWrapper<Address>().eq(Address::getDefaultAddress, 1).eq(Address::getUserId, userId)));
    }


}
