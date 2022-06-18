package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@Data
public class UserMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("微信用户id")
    private String openId;

    @ApiModelProperty("消息内容")
    private String message;

    @ApiModelProperty("创建日期")
    private String createTime;

}
