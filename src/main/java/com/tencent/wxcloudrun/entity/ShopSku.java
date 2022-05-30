package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 商城商品sku
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@TableName("shop_sku")
@ApiModel(value = "ShopSku对象", description = "商城商品sku")
@Data
public class ShopSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
      @TableId(value = "sku_id", type = IdType.AUTO)
    private Integer skuId;

    @ApiModelProperty("商品标题")
    private String title;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("商品价格")
    private BigDecimal price;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("重量（G）")
    private Double weight;

    @ApiModelProperty("商品主图")
    private String img;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("每个规格的具体值 格式: 尺码_S,颜色_经典款短袖黑色A")
    private String specification;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    @ApiModelProperty("副图")
    private List<SkuImg> skuImgs;



    @Override
    public String toString() {
        return "ShopSku{" +
        "skuId=" + skuId +
        ", title=" + title +
        ", description=" + description +
        ", price=" + price +
        ", stock=" + stock +
        ", weight=" + weight +
        ", img=" + img +
        ", unit=" + unit +
        ", status=" + status +
        ", specification=" + specification +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
