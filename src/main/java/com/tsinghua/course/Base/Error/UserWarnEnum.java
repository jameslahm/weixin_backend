package com.tsinghua.course.Base.Error;

/**
 * @描述 用户操作警告枚举
 **/
public enum UserWarnEnum implements ExceptionInterface {
    LOGIN_FAILED("UserWarn001", "用户或密码不正确"),

    NEED_LOGIN("UserWarn002", "用户未登录或登录已过期"),

    PERMISSION_DENIED("UserWarn003", "无权限访问对应内容"),

    REGISTER_FAILED("UserWarn004","user register failed"),

    BAD_REQUEST("UserWarn005","bad request"),

    NOT_FOUND("UserWarn006","not found");

    UserWarnEnum(String code, String msg) {
        errorCode = code;
        errorMsg = msg;
    }

    private String errorCode;
    private String errorMsg;
    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMsg;
    }
}
