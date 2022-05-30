package com.tencent.wxcloudrun.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 专业
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@ApiModel(value = "Major对象", description = "专业")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("专业码")
    private String majorCode;

    @ApiModelProperty("专业名称")
    private String majorName;


    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "Major{" +
        "majorCode=" + majorCode +
        ", majorName=" + majorName +
        "}";
    }
}
