package com.ferry.server.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/11
 */
@Data
@TableName(value = "bl_comment")
@EqualsAndHashCode(callSuper = false)
public class BlComment implements Serializable {
    /**
     * 唯一id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 回复评论的id
     */
    @TableField(value = "to_comment_id")
    private String toCommentId;

    /**
     * 回复个人的id
     */
    @TableField(value = "to_user_id")
    private String toUserId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 博客id
     */
    @TableField(value = "blog_id")
    private String blogId;

    /**
     * 状态
     */
    @TableField(value = "status")
    private int status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 评论来源： MESSAGE_BOARD，ABOUT，BLOG_INFO 等
     */
    @TableField(value = "source")
    private String source;

    /**
     * 评论类型 1:点赞 0:评论
     */
    @TableField(value = "TYPE")
    private Integer type;

    /**
     * 一级评论ID
     */
    @TableField(value = "first_comment_id")
    private String firstCommentId;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新人
     */
    @TableField(value = "last_update_by")
    private String lastUpdateBy;

    /**
     * 博客名
     */
    // 非数据库字段
    @TableField(exist = false)
    private String blogName;

    /**
     * 评论用户名
     */
    // 非数据库字段
    @TableField(exist = false)
    private String userName;

    /**
     * 回复评论用户名
     */
    // 非数据库字段
    @TableField(exist = false)
    private String toUserName;

    /**
     * 回复评论内容
     */
    // 非数据库字段
    @TableField(exist = false)
    private String toCommentName;

    @TableField(exist = false)
    private List <BlComment> commentList;

    @TableField(exist = false)
    private BlBlog blBlog;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_TO_COMMENT_ID = "to_comment_id";

    public static final String COL_TO_USER_ID = "to_user_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_BLOG_ID = "blog_id";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_SOURCE = "source";

    public static final String COL_TYPE = "TYPE";

    public static final String COL_FIRST_COMMENT_ID = "first_comment_id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}