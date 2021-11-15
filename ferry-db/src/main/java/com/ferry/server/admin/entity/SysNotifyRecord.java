package com.ferry.server.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
@Data
@TableName(value = "sys_notify_record")
@EqualsAndHashCode(callSuper = false)
public class SysNotifyRecord implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 通知通告ID
     */
    @TableField(value = "notify_id")
    private String notifyId;

    /**
     * 接受人
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 阅读标记
     */
    @TableField(value = "is_read")
    private int isRead;

    /**
     * 阅读时间
     */
    @TableField(value = "read_date")
    private Date readDate;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "last_update_by")
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    @TableField(value = "last_update_time")
    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NOTIFY_ID = "notify_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_IS_READ = "is_read";

    public static final String COL_READ_DATE = "read_date";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";

    public static final String COL_LAST_UPDATE_TIME = "last_update_time";
}