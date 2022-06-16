package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.entity.Admin;
import com.tencent.wxcloudrun.mapper.AdminMapper;
import com.tencent.wxcloudrun.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quinn
 * @since 2022-06-16
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
