package com.ferry.server.admin.entity;

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
 * @Date: 2021/9/12
 */
@Data
@TableName(value = "sys_notify")
public class SysNotify implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 附件
     */
    @TableField(value = "files")
    private String files;

    /**
     * 状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 备注信息
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 删除标记
     */
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 是否发送短信 1 是  0 否
     */
    @TableField(value = "sms_remind")
    private String smsRemind;

    /**
     * 接受人
     */
    @TableField(value = "user_id")
    private String userId;

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
     * 通知人id
     */
    @TableField(value = "create_user_id")
    private String createUserId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TYPE = "type";

    public static final String COL_TITLE = "title";

    public static final String COL_CONTENT = "content";

    public static final String COL_FILES = "files";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_REMARKS = "remarks";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_SMS_REMIND = "sms_remind";

    public static final String IS_READ = "is_read";

    public static final String USER_ID = "user_id";

    public static final String CRESARE_USER_ID = "create_user_id";
}