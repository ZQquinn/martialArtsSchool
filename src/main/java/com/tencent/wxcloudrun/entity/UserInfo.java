package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */

@Data
@TableName("user_info")
@ApiModel(value = "UserInfo对象", description = "用户信息")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("微信唯一标识")
    private String openId;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("在校时间")
    private String schoolAt;



    @ApiModelProperty("高考年份")
    private String collegeEntranceTime;

    @ApiModelProperty("毕业院校")
    private String graduateSchool;

    @ApiModelProperty("区域")
    private String region;

    @ApiModelProperty("城市编码")
    private String regionCode;

    @ApiModelProperty("工作单位")
    private String workUnit;

    @ApiModelProperty("行业")
    private String industry;

    @ApiModelProperty("职务")
    private String job;

    @ApiModelProperty("个人简介")
    private String profile;

    @ApiModelProperty("曾获奖励")
    private String rewark;

    @ApiModelProperty("头像URL")
    private String avatarUrl;

    @ApiModelProperty("是否允许找到我")
    private boolean isFlagFindMe;

    @ApiModelProperty("是否允许查看我")
    private boolean isFlagCheckMe;

    @ApiModelProperty("用户状态 1-审核中 2-已审核")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;



    @ApiModelProperty("专业集合")
    @TableField(exist = false)
    private List<String> majorCodes;


    @Override
    public String toString() {
        return "UserInfo{" +
        "id=" + id +
        ", openId=" + openId +
        ", phone=" + phone +
        ", userName=" + userName +
        ", schoolAt=" + schoolAt +
        ", majorCode=" + majorCodes +
        ", collegeEntranceTime=" + collegeEntranceTime +
        ", graduateSchool=" + graduateSchool +
        ", region=" + region +
        ", cityCode=" + regionCode +
        ", workUnit=" + workUnit +
        ", industry=" + industry +
        ", job=" + job +
        ", profile=" + profile +
        ", rewark=" + rewark +
        ", avatarUrl=" + avatarUrl +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
