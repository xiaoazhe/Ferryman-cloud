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

/**
 * @Author: 摆渡人
 * @Date: 2021/5/12
 */
@Data
@TableName(value = "bl_friend_link")
@EqualsAndHashCode(callSuper = false)
public class BlFriendLink implements Serializable {
    /**
     * 唯一uid
     */
    @TableId(value = "uid", type = IdType.INPUT)
    private String uid;

    /**
     * 友情链接标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 友情链接介绍
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 友情链接URL
     */
    @TableField(value = "url")
    private String url;

    /**
     * 点击数
     */
    @TableField(value = "click_count")
    private Integer clickCount;

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
     * 状态
     */
    @TableField(value = "status")
    private Byte status;

    /**
     * 排序字段，越大越靠前
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 友链状态： 0 申请中， 1：已上线，  2：已下架
     */
    @TableField(value = "link_status")
    private Integer linkStatus;

    /**
     * 申请用户UID
     */
    @TableField(value = "user_uid")
    private String userUid;

    /**
     * 操作管理员UID
     */
    @TableField(value = "admin_uid")
    private String adminUid;

    /**
     * 站长邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 网站图标
     */
    @TableField(value = "file_uid")
    private String fileUid;

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
     * 作者名
     */
    @TableField(value = "nick_name")
    private String nickName;

    private static final long serialVersionUID = 1L;

    public static final String COL_UID = "uid";

    public static final String COL_TITLE = "title";

    public static final String COL_SUMMARY = "summary";

    public static final String COL_URL = "url";

    public static final String COL_CLICK_COUNT = "click_count";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";

    public static final String COL_SORT = "sort";

    public static final String COL_LINK_STATUS = "link_status";

    public static final String COL_USER_UID = "user_uid";

    public static final String COL_ADMIN_UID = "admin_uid";

    public static final String COL_EMAIL = "email";

    public static final String COL_FILE_UID = "file_uid";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}