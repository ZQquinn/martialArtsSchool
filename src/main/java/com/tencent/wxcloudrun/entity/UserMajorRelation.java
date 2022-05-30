package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@TableName("user_major_relation")
@ApiModel(value = "UserMajorRelation对象", description = "")
public class UserMajorRelation implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("微信唯一标识")
    private String openId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("专业码")
    private String majorCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    @Override
    public String toString() {
        return "UserMajorRelation{" +
        "id=" + id +
        ", openId=" + openId +
        ", userId=" + userId +
        ", majorCode=" + majorCode +
        "}";
    }
}
