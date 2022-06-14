package com.tencent.wxcloudrun.dto;

import com.tencent.wxcloudrun.common.entity.PageQuery;
import com.tencent.wxcloudrun.entity.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQueryDto extends PageQuery<Order> {

    @ApiModelProperty("订单编号")
    private String outTradeNo;

    @ApiModelProperty("查询参数 商品名称或者订单编号")
    private String param;

    @ApiModelProperty("订单状态")
    private Integer status;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

}
