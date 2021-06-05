package com.ferry.common.enums;

import lombok.Getter;

/**
 * 状态码枚举。所有的状态码都在这里编写
 *
 * @Author: 码仔
 * @Date: 2020/2/9 14:19
 * @Version 1.0
 */
@Getter
public enum StateEnums {

    /**
     * 审核状态
     */
    NOT_CHECK(2, "审核驳回"),
    CHECK(0, "审核中"),
    SUC_CHECK(1, "审核成功"),

    /**
     * 逻辑删除状态
     */
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除"),

    /**
     * 启用状态
     */
    ENABLED(1, "启用"),
    NOT_ENABLE(0, "未启用"),

    /**
     * 性别状态
     */
    SEX_MAN(1, "男"),
    SEX_WOMAN(2, "女"),

    /**
     * 请求访问状态枚举
     */
    REQUEST_SUCCESS(1, "请求正常"),
    REQUEST_ERROR(0, "请求异常"),

    /**
     * 用户标识。
     * 0表示管理员，1表示普通用户
     */
    ADMIN(0, "管理员"),
    USER(1, "普通用户"),

    /**
     * 注册状态。
     * 0表示管理员，1表示普通用户
     */
    REGISTER_ERR(0, "注册失败"),
    REGISTER_SUC(1, "注册成功");

    private Integer code;
    private String msg;

    StateEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
