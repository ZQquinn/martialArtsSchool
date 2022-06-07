package com.tencent.wxcloudrun.dto;


import com.tencent.wxcloudrun.common.entity.PageQuery;
import com.tencent.wxcloudrun.entity.ShopSku;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品查询条件")
public class ShopSkuDto extends PageQuery<ShopSku> {

    @ApiModelProperty("商品名称")
    private String title;

    @ApiModelProperty("状态 1-上架 2-下架 0-删除")
    private Integer status;
}
