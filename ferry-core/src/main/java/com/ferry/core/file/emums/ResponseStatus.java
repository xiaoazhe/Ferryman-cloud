package com.ferry.core.file.emums;


public enum ResponseStatus {

    SUCCESS(200, "操作成功！"),
    ERROR(500, "服务器未知错误！"),
    INVALID_PARAMS(500, "操作失败，无效的参数，请检查参数格式、类型是否正确！"),
    UPLOAD_FILE_ERROR(500, "文件上传失败！"),
    ;

    private Integer code;
    private String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseStatus getResponseStatus(String message) {
        for (ResponseStatus ut : ResponseStatus.values()) {
            if (ut.getMessage() == message) {
                return ut;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
