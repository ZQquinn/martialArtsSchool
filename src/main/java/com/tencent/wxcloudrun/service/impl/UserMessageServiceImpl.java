package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.entity.UserMessage;
import com.tencent.wxcloudrun.mapper.UserMessageMapper;
import com.tencent.wxcloudrun.service.IUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户消息 服务实现类
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements IUserMessageService {

}
