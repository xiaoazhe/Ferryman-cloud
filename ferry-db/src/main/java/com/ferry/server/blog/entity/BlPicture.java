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
 * @Date: 2021/5/21
 */
@Data
@TableName(value = "bl_picture")
public class BlPicture implements Serializable {
    /**
     * 唯一uid
     */
    @TableId(value = "uid", type = IdType.INPUT)
    private String uid;

    /**
     * 图片uid
     */
    @TableField(value = "file_uid")
    private String fileUid;

    /**
     * 图片名
     */
    @TableField(value = "pic_name")
    private String picName;

    /**
     * 分类uid
     */
    @TableField(value = "picture_sort_uid")
    private String pictureSortUid;

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

    public static final String COL_PIC_NAME = "pic_name";

    public static final String COL_PICTURE_SORT_UID = "picture_sort_uid";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}