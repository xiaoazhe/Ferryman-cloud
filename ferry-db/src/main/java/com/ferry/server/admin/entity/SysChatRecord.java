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
@TableName(value = "sys_chat_record")
public class SysChatRecord implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 消息内容
     */
    @TableField(value = "msg_content")
    private String msgContent;

    /**
     * 发送时间
     */
    @TableField(value = "send_time")
    private Date sendTime;

    /**
     * 发送人id
     */
    @TableField(value = "sender_id")
    private String senderId;

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

    public static final String COL_MSG_CONTENT = "msg_content";

    public static final String COL_SEND_TIME = "send_time";

    public static final String COL_SENDER_ID = "sender_id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";

    public static final String COL_LAST_UPDATE_TIME = "last_update_time";
}