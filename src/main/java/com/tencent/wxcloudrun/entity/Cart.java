package com.tencent.wxcloudrun.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@ApiModel(value = "Cart对象", description = "购物车")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品ID")
    private Integer skuId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("数量")
    private Integer num;


    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Cart{" +
        "skuId=" + skuId +
        ", userId=" + userId +
        ", num=" + num +
        "}";
    }
}
