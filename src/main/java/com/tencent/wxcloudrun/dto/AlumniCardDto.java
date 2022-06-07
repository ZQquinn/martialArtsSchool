package com.tencent.wxcloudrun.dto;

import com.tencent.wxcloudrun.common.entity.PageQuery;
import com.tencent.wxcloudrun.entity.AlumniCard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "校友卡查询条件")
public class AlumniCardDto extends PageQuery<AlumniCard> {

    @ApiModelProperty("审核状态 1-审核中 2-已审核")
    private int status;

    @ApiModelProperty("校友名称")
    private String userName;

}
