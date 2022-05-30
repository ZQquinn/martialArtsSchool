package com.tencent.wxcloudrun.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tencent.wxcloudrun.exception.CommonEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;
    /**
     * 返回结果
     */
    private T data;
    /**
     * 返回消息
     */
    private String msg;

    private boolean success = true;

    @JsonIgnore
    private CommonEnum resultCode;

    private JsonResult() {
    }

    public void setResultCode(CommonEnum resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.getResultCode();
        this.msg = resultCode.getResultMsg();
    }

    public JsonResult(CommonEnum resultCode, T data) {
        this.code = resultCode.getResultCode();
        this.msg = resultCode.getResultMsg();
        this.data = data;
    }

    public static <T> JsonResult<T> success() {
        JsonResult<T> result = new JsonResult<>();
        result.setResultCode(CommonEnum.SUCCESS);
        return result;
    }

    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> result = new JsonResult<>();
        result.setResultCode(CommonEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> JsonResult<T> error(CommonEnum resultCode) {
        JsonResult<T> result = new JsonResult<>();
        result.setResultCode(resultCode);
        return result;
    }
}
