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
     * 参数状态
     */
    PARAMETER_SUC(1, "参数正常"),
    PARAMETER_ERROR(0, "参数异常"),

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
     * 0注册失败，1注册成功
     */
    REGISTER_ERR(0, "注册失败"),
    REGISTER_SUC(1, "注册成功"),

    /**
     * 收藏状态。
     * 0收藏失败，1收藏成功
     */
    COLLECT_REPEAT(3, "不要重复收藏"),
    COLLECT_ERR(0, "收藏失败"),
    COLLECT_SUC(1, "收藏成功"),
    /**
     * 发布状态。
     * 0发布失败，1发布成功
     */
    SAVEBLOG_ERR(0, "发布失败"),
    SAVEBLOG_SUC(1, "发布成功"),

    /**
     * 点赞。
     * 0点赞失败，1点赞成功
     */
    ADDLIKE_ERR(0, "点赞失败"),
    ADDLIKE_SUC(1, "点赞成功"),

    /**
     * 注册状态。
     * 0评论失败，1评论成功
     */
    COMMENT_ERR(0, "评论失败"),
    COMMENT_SUC(1, "评论成功");

    private Integer code;
    private String msg;

    StateEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
