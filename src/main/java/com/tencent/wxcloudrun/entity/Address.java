package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 地址
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@ApiModel(value = "Address对象", description = "地址")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("行政区/镇")
    private String county;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("是否是默认地址")
    private boolean defaultAddress;

    @ApiModelProperty("所属用户ID")
    private Integer userId;

    @ApiModelProperty("签收手机号")
    private String phone;

    private String consignee;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    @Override
    public String toString() {
        return "Address{" +
        "id=" + id +
        ", province=" + province +
        ", city=" + city +
        ", county=" + county +
        ", address=" + address +
        ", defaultAddress=" + defaultAddress +
        ", userId=" + userId +
        ", phone=" + phone +
        ", consignee=" + consignee +
        "}";
    }
}
