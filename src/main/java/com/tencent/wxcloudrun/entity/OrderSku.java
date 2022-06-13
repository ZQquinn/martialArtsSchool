package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 订单商品
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@TableName("order_sku")
@ApiModel(value = "OrderSku对象", description = "订单商品")
@Data
public class OrderSku implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品规格ID")
    private Integer skuId;

    @ApiModelProperty("订单主表ID")
    private Integer orderId;

    @ApiModelProperty("订单主表串号")
    private String orderNo;

    @ApiModelProperty("SKU 标题")
    private String skuTitle;

    @ApiModelProperty("商品数量")
    private Integer num;

    @ApiModelProperty("商品单价")
    private BigDecimal price;

    @ApiModelProperty("主图 优先使用SKU图片")
    private String img;

    @ApiModelProperty("商品单位")
    private String unit;

    @ApiModelProperty("商品重量")
    private Double weight;

    @TableField(exist = false)
    @ApiModelProperty("副图")
    private List<SkuImg> skuImgs;

    @TableField(exist = false)
    @ApiModelProperty("sku属性")
    private List<SkuAttributes> skuAttributes;

    @Override
    public String toString() {
        return "OrderSku{" +
        "id=" + id +
        ", skuId=" + skuId +
        ", orderId=" + orderId +
        ", orderNo=" + orderNo +
        ", skuTitle=" + skuTitle +
        ", num=" + num +
        ", price=" + price +
        ", img=" + img +
        ", unit=" + unit +
        ", weight=" + weight +
        "}";
    }
}
