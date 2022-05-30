package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

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
