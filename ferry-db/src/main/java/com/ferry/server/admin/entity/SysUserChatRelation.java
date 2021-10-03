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
 * @Date: 2021/10/3
 */
@Data
@TableName(value = "sys_user_chat_relation")
public class SysUserChatRelation implements Serializable {
    /**
     * 用户id
     */
    @TableField(value = "sender_id")
    private String senderId;

    /**
     * 接收人id
     */
    @TableField(value = "receiver_id")
    private String receiverId;

    /**
     * 聊天列表id
     */
    @TableField(value = "chat_id")
    private String chatId;

    /**
     * 是否已读
     */
    @TableField(value = "is_read")
    private Boolean isRead;

    /**
     * 是否删除0否1是
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

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

    public static final String COL_SENDER_ID = "sender_id";

    public static final String COL_RECEIVER_ID = "receiver_id";

    public static final String COL_CHAT_ID = "chat_id";

    public static final String COL_IS_READ = "is_read";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";

    public static final String COL_LAST_UPDATE_TIME = "last_update_time";
}