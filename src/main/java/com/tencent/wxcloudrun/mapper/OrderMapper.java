package com.tencent.wxcloudrun.mapper;

import com.tencent.wxcloudrun.dto.OrderQueryDto;
import com.tencent.wxcloudrun.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
public interface OrderMapper extends BaseMapper<Order> {


//    List<Order> selectOrderPages(@Param("orderQueryDto")OrderQueryDto orderQueryDto);

}
