package com.tencent.wxcloudrun.vo;

import com.tencent.wxcloudrun.entity.Major;
import com.tencent.wxcloudrun.entity.Region;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class UserInfoVo {


    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("在校时间")
    private String schoolAt;

    @ApiModelProperty("高考年份")
    private String collegeEntranceTime;

    @ApiModelProperty("毕业院校")
    private String graduateSchool;

    @ApiModelProperty("区域实体")
    private Region regionEntity;

    @ApiModelProperty("工作单位")
    private String workUnit;

    @ApiModelProperty("行业")
    private String industry;

    @ApiModelProperty("个人简介")
    private String profile;

    @ApiModelProperty("曾获奖励")
    private String rewark;

    @ApiModelProperty("头像URL")
    private String avatarUrl;

    @ApiModelProperty("专业集合")
    private List<Major> majors;
}
