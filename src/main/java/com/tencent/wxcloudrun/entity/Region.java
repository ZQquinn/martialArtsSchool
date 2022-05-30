package com.tencent.wxcloudrun.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 省市、国家
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@ApiModel(value = "Region对象", description = "省市、国家")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("区域编码")
    private String code;

    @ApiModelProperty("父级区域")
    private String regionParent;

    @ApiModelProperty("所在地")
    private String regionLocation;

    @ApiModelProperty("区域类型 1—国内 2-国外")
    private Integer type;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegionParent() {
        return regionParent;
    }

    public void setRegionParent(String regionParent) {
        this.regionParent = regionParent;
    }

    public String getRegionLocation() {
        return regionLocation;
    }

    public void setRegionLocation(String regionLocation) {
        this.regionLocation = regionLocation;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Region{" +
        "code=" + code +
        ", regionParent=" + regionParent +
        ", regionLocation=" + regionLocation +
        ", type=" + type +
        "}";
    }
}
