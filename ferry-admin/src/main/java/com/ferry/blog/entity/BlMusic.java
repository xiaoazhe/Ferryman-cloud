package com.ferry.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/10
 */
@Data
@TableName(value = "bl_music")
public class BlMusic implements Serializable {
    /**
     * 音乐id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 歌手
     */
    @TableField(value = "artist")
    private String artist;

    /**
     * 歌曲链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 歌词
     */
    @TableField(value = "lrc")
    private String lrc;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 是否启用，0否1是
     */
    @TableField(value = "enable")
    private Integer enable;

    /**
     * 是否删除，0否1是
     */
    @TableField(value = "deleted")
    private Integer deleted;

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
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_ARTIST = "artist";

    public static final String COL_URL = "url";

    public static final String COL_COVER = "cover";

    public static final String COL_LRC = "lrc";

    public static final String COL_CREATED_TIME = "created_time";

    public static final String COL_ENABLE = "enable";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}