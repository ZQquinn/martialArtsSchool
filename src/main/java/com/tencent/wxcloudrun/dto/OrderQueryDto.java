package com.tencent.wxcloudrun.dto;

import com.tencent.wxcloudrun.common.entity.PageQuery;
import com.tencent.wxcloudrun.entity.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQueryDto extends PageQuery<Order> {

    private String outTradeNo;

}
