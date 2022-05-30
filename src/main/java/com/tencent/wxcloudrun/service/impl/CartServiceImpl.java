package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.entity.Cart;
import com.tencent.wxcloudrun.mapper.CartMapper;
import com.tencent.wxcloudrun.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
