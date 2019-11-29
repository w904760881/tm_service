package com.schindler.ioee.tm_service.response;

/**
 * @author weihao2
 */

public enum RestResponseEnum {
    /* */
    SUCCESS(200, "成功"),
    UNKNOWN_ERROR(-1, "未知错误"),

    EQUIPMENT_ERROR(511, "操作设备失败"),

    EXCEL_DOWNLOAD_ERROR(516, "EXCEL下载失败"),
    EXCEL_UPLOAD_ERROR(517, "EXCEL上传失败"),
    /* error result about login*/
    LOGIN_ERROR(501, "用户登录失败"),
    ADMIN_LOGIN_ERROR(502, "管理员登录失败"),

    /*  error result about user*/
    USER_ERROR(506, "操作用户失败");

    private Integer code;
    private String msg;

    RestResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
