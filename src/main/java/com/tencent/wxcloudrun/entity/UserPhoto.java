package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户拾忆照片
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@TableName("user_photo")
@ApiModel(value = "UserPhoto对象", description = "用户拾忆照片")
@Data
public class UserPhoto implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("微信用户id")
    private String openId;

    @ApiModelProperty("照片url")
    private String img;

    @ApiModelProperty("状态 1-上传完成 2-审核中 ")
    private Integer status;

    @ApiModelProperty("相册id")
    private Integer albumId;



    @Override
    public String toString() {
        return "UserPhoto{" +
        "id=" + id +
        ", userId=" + userId +
        ", openId=" + openId +
        ", img=" + img +
        ", status=" + status +
        "}";
    }
}
