package com.tencent.wxcloudrun.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum OrderStatus implements IEnum<Integer> {

    UNPAY(10, "待付款"),
    GROUP_SHOP_WAIT(12,"等待团购活动结束"),
    WAIT_STOCK(20, "待出库"),
    WAIT_CONFIRM(30, "待收货"),
    WAIT_APPRAISE(40, "待评价"),


    WAITING_SHIPPED(11, "待发货"),
    SHIPPED(13, "已发货"),
    COMPLETE(50, "已完成"),
    REFUNDING(60, "退款中"),
    REFUNDED(70, "已退款"),
    CANCELED(80, "已取消"),
    CANCELED_SYS(90, "已取消（系统）");

    private int code;
    private String desc;

    OrderStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }



}
