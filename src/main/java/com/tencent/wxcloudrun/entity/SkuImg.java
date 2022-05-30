package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 商品sku副图
 * </p>
 *
 * @author quinn
 * @since 2022-05-27
 */
@TableName("sku_img")
@ApiModel(value = "SkuImg对象", description = "商品sku副图")
public class SkuImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品sku id")
    private Integer skuId;

    @ApiModelProperty("商品图片")
    private String imgUrl;


    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "SkuImg{" +
        "skuId=" + skuId +
        ", imgUrl=" + imgUrl +
        "}";
    }
}
