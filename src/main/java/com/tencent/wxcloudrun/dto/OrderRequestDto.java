package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "下单传输实体")
public class OrderRequestDto {


    @ApiModelProperty("商品列表")
    private List<OrderRequestSkuDto> skuList;

    /**
     * 商品支付总价
     */
    @ApiModelProperty("商品支付总价")
    private Integer totalPrice;

    @ApiModelProperty("商品原始总价（仅显示作用）")
    private Integer totalOriginalPrice;

    @ApiModelProperty("用户地址ID")
    private Long addressId;



    @ApiModelProperty("用户订单备注")
    private String mono;

    /**
     * 购物车 ？ 直接点击购买商品 cart,buy
     */
    @ApiModelProperty("购物车 ？ 直接点击购买商品 cart,buy")
    private String takeWay;

    @ApiModelProperty("运费")
    private Integer freightPrice;

//    @ApiField(description = "前端计算后期望的订单价格")
//    private Integer exceptPrice;
}
