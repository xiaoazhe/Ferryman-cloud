package com.ferry.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@Data
@TableName(value = "f_blog")
public class FBlog implements Serializable {
    /**
     * 唯一uid
     */
    @TableId(value = "uid", type = IdType.INPUT)
    private String uid;

    /**
     * 唯一oid
     */
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    /**
     * 博客标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 博客简介
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 博客内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 标签uid
     */
    @TableField(value = "tag_uid")
    private String tagUid;

    /**
     * 博客点击数
     */
    @TableField(value = "click_count")
    private Integer clickCount;

    /**
     * 博客收藏数
     */
    @TableField(value = "collect_count")
    private Integer collectCount;

    /**
     * 标题图片uid
     */
    @TableField(value = "file_uid")
    private String fileUid;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 管理员uid
     */
    @TableField(value = "admin_uid")
    private String adminUid;

    /**
     * 是否原创（0:不是 1：是）
     */
    @TableField(value = "is_original")
    private String isOriginal;

    /**
     * 作者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 文章出处
     */
    @TableField(value = "articles_part")
    private String articlesPart;

    /**
     * 博客分类UID
     */
    @TableField(value = "blog_sort_uid")
    private String blogSortUid;

    /**
     * 推荐等级(0:正常)
     */
    @TableField(value = "level")
    private Boolean level;

    /**
     * 是否发布：0：否，1：是
     */
    @TableField(value = "is_publish")
    private String isPublish;

    /**
     * 排序字段
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 是否开启评论(0:否 1:是)
     */
    @TableField(value = "open_comment")
    private Boolean openComment;

    /**
     * 类型【0 博客， 1：推广】
     */
    @TableField(value = "type")
    private Boolean type;

    /**
     * 外链【如果是推广，那么将跳转到外链】
     */
    @TableField(value = "outside_link")
    private String outsideLink;

    /**
     * 投稿用户UID
     */
    @TableField(value = "user_uid")
    private String userUid;

    /**
     * 文章来源【0 后台添加，1 用户投稿】
     */
    @TableField(value = "article_source")
    private Boolean articleSource;

    private static final long serialVersionUID = 1L;

    public static final String COL_UID = "uid";

    public static final String COL_OID = "oid";

    public static final String COL_TITLE = "title";

    public static final String COL_SUMMARY = "summary";

    public static final String COL_CONTENT = "content";

    public static final String COL_TAG_UID = "tag_uid";

    public static final String COL_CLICK_COUNT = "click_count";

    public static final String COL_COLLECT_COUNT = "collect_count";

    public static final String COL_FILE_UID = "file_uid";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_ADMIN_UID = "admin_uid";

    public static final String COL_IS_ORIGINAL = "is_original";

    public static final String COL_AUTHOR = "author";

    public static final String COL_ARTICLES_PART = "articles_part";

    public static final String COL_BLOG_SORT_UID = "blog_sort_uid";

    public static final String COL_LEVEL = "level";

    public static final String COL_IS_PUBLISH = "is_publish";

    public static final String COL_SORT = "sort";

    public static final String COL_OPEN_COMMENT = "open_comment";

    public static final String COL_TYPE = "type";

    public static final String COL_OUTSIDE_LINK = "outside_link";

    public static final String COL_USER_UID = "user_uid";

    public static final String COL_ARTICLE_SOURCE = "article_source";
}