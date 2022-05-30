package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getGraduateImg() {
        return graduateImg;
    }

    public void setGraduateImg(String graduateImg) {
        this.graduateImg = graduateImg;
    }

    public String getDegreeImg() {
        return degreeImg;
    }

    public void setDegreeImg(String degreeImg) {
        this.degreeImg = degreeImg;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
