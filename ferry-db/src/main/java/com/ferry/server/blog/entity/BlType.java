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
 * @Date: 2021/5/7
 */
@Data
@TableName(value = "bl_type")
@EqualsAndHashCode(callSuper = false)
public class BlType implements Serializable {
    public static final String COL_TYPE_ID = "type_id";
    public static final String COL_TYPE_NAME = "type_name";
    public static final String COL_TYPE_BLOG_COUNT = "type_blog_count";
    public static final String COL_ENABLE = "enable";
    public static final String COL_DELETED = "deleted";
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "pid")
    private Long pid;

    /**
     * 文章类型名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 类型介绍
     */
    @TableField(value = "description")
    private String description;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 是否可用
     */
    @TableField(value = "available")
    private Boolean available;

    /**
     * 添加时间
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
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新人
     */
    @TableField(value = "last_update_by")
    private String lastUpdateBy;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PID = "pid";

    public static final String COL_NAME = "name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_SORT = "sort";

    public static final String COL_ICON = "icon";

    public static final String COL_AVAILABLE = "available";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}