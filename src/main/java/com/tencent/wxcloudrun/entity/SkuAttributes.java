package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author quinn
 * @since 2022-06-07
 */
@TableName("sku_attributes")
@ApiModel(value = "SkuAttributes对象", description = "Sku属性值")
public class SkuAttributes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    private Integer skuId;

    @ApiModelProperty("属性名")
    private String attributeName;

    @ApiModelProperty("属性值")
    private String attributeKey;


    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    @Override
    public String toString() {
        return "SkuAttributes{" +
        "skuId=" + skuId +
        ", attributeName=" + attributeName +
        ", attributeKey=" + attributeKey +
        "}";
    }
}
