package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.entity.Region;
import com.tencent.wxcloudrun.mapper.RegionMapper;
import com.tencent.wxcloudrun.service.IRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 省市、国家 服务实现类
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

}
