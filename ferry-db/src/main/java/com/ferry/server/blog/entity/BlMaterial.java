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
 * @Date: 2021/6/17
 */
@Data
@TableName(value = "bl_material")
public class BlMaterial implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 链接
     */
    @TableField(value = "`url`")
    private String url;

    /**
     * 提取码
     */
    @TableField(value = "`pwd`")
    private String pwd;

    /**
     * 描述
     */
    @TableField(value = "`describe`")
    private String describe;

    /**
     * 类型
     */
    @TableField(value = "`type`")
    private String type;

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

    /**
     * 排序 优先级
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 0禁用 1正常
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 封面url
     */
    @TableField(value = "file_url")
    private String fileUrl;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_URL = "url";

    public static final String COL_PWD = "pwd";

    public static final String COL_DESCRIBE = "describe";

    public static final String COL_TYPE = "type";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";

    public static final String COL_SORT = "sort";

    public static final String COL_STATUS = "status";
}