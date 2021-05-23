package com.ferry.server.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/23
 */
@Data
@TableName(value = "bl_picture_sort")
public class BlPictureSort implements Serializable {
    /**
     * 唯一uid
     */
    @TableId(value = "uid", type = IdType.INPUT)
    private String uid;

    /**
     * 分类图片uid
     */
    @TableField(value = "file_uid")
    private String fileUid;

    /**
     * 分类名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Byte status;

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

    @TableField(value = "parent_uid")
    private String parentUid;

    /**
     * 排序字段，越大越靠前
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 是否显示，1：是，0，否
     */
    @TableField(value = "is_show")
    private Boolean isShow;

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

    public static final String COL_UID = "uid";

    public static final String COL_FILE_UID = "file_uid";

    public static final String COL_NAME = "name";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_PARENT_UID = "parent_uid";

    public static final String COL_SORT = "sort";

    public static final String COL_IS_SHOW = "is_show";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}