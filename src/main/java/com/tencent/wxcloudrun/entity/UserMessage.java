package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户消息
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@TableName("user_message")
@ApiModel(value = "UserMessage对象", description = "用户消息")
public class UserMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("微信用户id")
    private Integer openId;

    @ApiModelProperty("消息内容")
    private String message;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOpenId() {
        return openId;
    }

    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
        "userId=" + userId +
        ", openId=" + openId +
        ", message=" + message +
        "}";
    }
}
