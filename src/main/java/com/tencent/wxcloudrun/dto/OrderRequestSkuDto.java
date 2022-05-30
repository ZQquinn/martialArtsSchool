package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("订单请求商品传输实体")
public class OrderRequestSkuDto {

    @ApiModelProperty("商品ID")
    private Integer skuId;

//    @ApiModelProperty( "商品ID")
//    private Long spuId;

    @ApiModelProperty("商品数量")
    private Integer num;

    /**
     * 以下信息仅在预览时使用，是前端传过来的，是不可靠的，不可在结算中使用
     **/
    @ApiModelProperty("商品价格")
    private Integer price;

//    @ApiModelProperty(description = "Vip价格")
//    private Integer vipPrice;

    @ApiModelProperty("商品重量（G）")
    private Integer weight;

    @ApiModelProperty("运费模板ID")
    private Long freightTemplateId;
}
