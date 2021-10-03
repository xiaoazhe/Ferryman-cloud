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
@TableName(value = "sys_user_group_info")
public class SysUserGroupInfo implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 用户组名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户组描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

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

    public static final String COL_NAME = "name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";

    public static final String COL_LAST_UPDATE_TIME = "last_update_time";
}