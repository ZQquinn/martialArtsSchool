package com.tencent.wxcloudrun.mapper;

import com.tencent.wxcloudrun.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;

/**
 * <p>
 * 地址 Mapper 接口
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Resource
public interface AddressMapper extends BaseMapper<Address> {

}
