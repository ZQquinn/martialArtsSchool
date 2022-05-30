package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户设置
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@TableName("user_set_up")
@ApiModel(value = "UserSetUp对象", description = "用户设置")
@Data
public class UserSetUp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("微信用户标识")
    private String openId;

    @ApiModelProperty("是否允许找到我")
    private boolean isFlagFindMe;

    @ApiModelProperty("是否允许查看我")
    private boolean isFlagCheckMe;


    public Integer getUserId() {
        return userId;
    }



    @Override
    public String toString() {
        return "UserSetUp{" +
        "userId=" + userId +
        ", openId=" + openId +
        ", isFlagFindMe=" + isFlagFindMe +
        ", isFlagCheckMe=" + isFlagCheckMe +
        "}";
    }
}
