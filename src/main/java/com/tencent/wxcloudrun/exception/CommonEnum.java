package com.tencent.wxcloudrun.exception;

public enum CommonEnum implements BaseErrorInfoInterface{
    // 数据操作错误定义
    SUCCESS(200, "成功!"),
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配!"),
    NOT_FOUND(404, "请求的资源不存在!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!"),
    PARAMETER_EXCEPTION(501, "请求参数校验异常"),

    /* 业务状态码 */
    USER_NOT_EXIST_ERROR(10001, "用户不存在"),
    TOKEN_NO_EXIST(10002, "token不存在"),
    TOKEN_PARSING_FAILED(10003, "token解析失败"),
    TOKEN_CHECK_FAILED(10003, "token验证失败"),

    ;

    /** 错误码 */
    private int resultCode;

    /** 错误描述 */
    private String resultMsg;

    CommonEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public int getResultCode() {
        return 0;
    }

    @Override
    public String getResultMsg() {
        return null;
    }



}
