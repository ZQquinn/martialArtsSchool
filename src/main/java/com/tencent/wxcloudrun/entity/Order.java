package com.tencent.wxcloudrun.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tencent.wxcloudrun.enums.OrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@ApiModel(value = "Order对象", description = "订单")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("下单渠道")
    private String channel;

    @ApiModelProperty("订单父单串号")
    private String parentOrderNo;

    @ApiModelProperty("订单串号")
    private String orderNo;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("订单状态")
    private OrderStatus status;

    @ApiModelProperty("商品配送费")
    private BigDecimal freightPrice;

    @ApiModelProperty("商品总价")
    private BigDecimal price;

    @ApiModelProperty("支付金额")
    private BigDecimal payPrice;

    @ApiModelProperty("支付流水号 (第三方)")
    private String payId;

    @ApiModelProperty("选择支付的AppId")
    private Integer appId;

    @ApiModelProperty("运单号")
    private String shipNo;

    @ApiModelProperty("签收人联系电话")
    private String phone;

    @ApiModelProperty("签收人姓名")
    private String consignee;

    @ApiModelProperty("用户订单备注")
    private String mono;

    @ApiModelProperty("客服订单备注")
    private String adminMono;

    @ApiModelProperty("用户申请退款原因")
    private String refundReason;

    @ApiModelProperty("下单时间")
    private DateTime orderTime;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty("发货时间")
    private LocalDateTime gmtShip;

    @ApiModelProperty("确认收货时间")
    private LocalDateTime gmtConfirm;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;




    @Override
    public String toString() {
        return "Order{" +
        "id=" + id +
        ", channel=" + channel +
        ", parentOrderNo=" + parentOrderNo +
        ", orderNo=" + orderNo +
        ", userId=" + userId +
        ", status=" + status +
        ", freightPrice=" + freightPrice +
        ", price=" + price +
        ", payPrice=" + payPrice +
        ", payId=" + payId +
        ", appId=" + appId +
        ", shipNo=" + shipNo +
        ", phone=" + phone +
        ", consignee=" + consignee +
        ", mono=" + mono +
        ", adminMono=" + adminMono +
        ", refundReason=" + refundReason +
        ", orderTime=" + orderTime +
        ", payTime=" + payTime +
        ", gmtShip=" + gmtShip +
        ", gmtConfirm=" + gmtConfirm +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
