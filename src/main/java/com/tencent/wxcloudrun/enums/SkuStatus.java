package com.tencent.wxcloudrun.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum SkuStatus implements IEnum<Integer> {

    SELLING(1,"销售中"),
    STOP_SELL(2,"停售"),
    DELETE(0,"删除"),
    ;

    private int code;
    private String desc;
    SkuStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    @Override
    public Integer getValue() {
        return null;
    }
}
