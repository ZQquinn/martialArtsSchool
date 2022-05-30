package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.entity.UserInfo;
import com.tencent.wxcloudrun.mapper.UserInfoMapper;
import com.tencent.wxcloudrun.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 校友卡 服务实现类
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
