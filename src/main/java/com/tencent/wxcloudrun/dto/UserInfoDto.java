package com.tencent.wxcloudrun.dto;

import com.tencent.wxcloudrun.common.entity.PageQuery;
import com.tencent.wxcloudrun.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "校友查询条件")
public class UserInfoDto extends PageQuery<UserInfo> {

    @ApiModelProperty("审核状态 1-审核中 2-已审核")
    private int status;

    @ApiModelProperty("校友名称")
    private String userName;

    @ApiModelProperty("入校时间")
    private String startTime;

    @ApiModelProperty("离校时间")
    private String endTime;

    @ApiModelProperty("高考年份")
    private String collegeEntranceTime;

    @ApiModelProperty("区域")
    private String region;

    @ApiModelProperty("专业编码")
    private List<String> majorCodes;
}
