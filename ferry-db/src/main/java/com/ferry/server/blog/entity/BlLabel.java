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
 * @Date: 2021/6/9
 */
@Data
@TableName(value = "bl_label")
public class BlLabel implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名称
     */
    @TableField(value = "labelname")
    private String labelname;

    /**
     * 状态
     */
    @TableField(value = "state")
    private String state;

    /**
     * 使用数量
     */
    @TableField(value = "count")
    private Integer count;

    /**
     * 关注数
     */
    @TableField(value = "fans")
    private String fans;

    /**
     * 是否推荐
     */
    @TableField(value = "recommend")
    private String recommend;

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
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_LABELNAME = "labelname";

    public static final String COL_STATE = "state";

    public static final String COL_COUNT = "count";

    public static final String COL_FANS = "fans";

    public static final String COL_RECOMMEND = "recommend";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}