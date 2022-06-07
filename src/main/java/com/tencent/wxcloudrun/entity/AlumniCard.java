package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 校友卡
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@TableName("alumni_card")
@ApiModel(value = "AlumniCard对象", description = "校友卡")
@Data
public class AlumniCard implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("校友卡")
    private Long cardId;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("证件类型")
    private String cardType;

    @ApiModelProperty("身份证号")
    private String cardNo;

    @ApiModelProperty("毕业证")
    private String graduateImg;

    @ApiModelProperty("学位证")
    private String degreeImg;

    @ApiModelProperty("证件照")
    private String cardImg;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("状态 1-待审核 2-已审核")
    private Integer status;

    @Override
    public String toString() {
        return "AlumniCard{" +
        "id=" + id +
        ", sex=" + sex +
        ", cardType=" + cardType +
        ", cardNo=" + cardNo +
        ", graduateImg=" + graduateImg +
        ", degreeImg=" + degreeImg +
        ", cardImg=" + cardImg +
        ", userId=" + userId +
        "}";
    }
}
