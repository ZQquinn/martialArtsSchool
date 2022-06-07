package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.entity.Album;
import com.tencent.wxcloudrun.mapper.AlbumMapper;
import com.tencent.wxcloudrun.service.IAlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 相册 服务实现类
 * </p>
 *
 * @author quinn
 * @since 2022-06-07
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements IAlbumService {

}
