package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.entity.Address;
import com.tencent.wxcloudrun.mapper.AddressMapper;
import com.tencent.wxcloudrun.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地址 服务实现类
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
