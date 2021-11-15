package com.ferry.server.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
@Data
@TableName(value = "bl_file")
@EqualsAndHashCode(callSuper = false)
public class BlFile implements Serializable {
    @TableId(value = "id")
    private String id;

    /**
     * 上传用户
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 文件存储类型
     */
    @TableField(value = "storage_type")
    private String storageType;

    /**
     * 文件名
     */
    @TableField(value = "original_file_name")
    private String originalFileName;

    /**
     * 大小
     */
    @TableField(value = "size")
    private Long size;

    /**
     * 后缀
     */
    @TableField(value = "suffix")
    private String suffix;

    /**
     * 宽
     */
    @TableField(value = "width")
    private Integer width;

    /**
     * 高
     */
    @TableField(value = "height")
    private Integer height;

    /**
     * 全部路径
     */
    @TableField(value = "file_path")
    private String filePath;

    /**
     * 文件路径
     */
    @TableField(value = "full_file_path")
    private String fullFilePath;

    @TableField(value = "file_hash")
    private String fileHash;

    /**
     * 上传类型
     */
    @TableField(value = "upload_type")
    private String uploadType;

    /**
     * 上传开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "upload_start_time")
    private Date uploadStartTime;

    /**
     * 上传结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "upload_end_time")
    private Date uploadEndTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_STORAGE_TYPE = "storage_type";

    public static final String COL_ORIGINAL_FILE_NAME = "original_file_name";

    public static final String COL_SIZE = "size";

    public static final String COL_SUFFIX = "suffix";

    public static final String COL_WIDTH = "width";

    public static final String COL_HEIGHT = "height";

    public static final String COL_FILE_PATH = "file_path";

    public static final String COL_FULL_FILE_PATH = "full_file_path";

    public static final String COL_FILE_HASH = "file_hash";

    public static final String COL_UPLOAD_TYPE = "upload_type";

    public static final String COL_UPLOAD_START_TIME = "upload_start_time";

    public static final String COL_UPLOAD_END_TIME = "upload_end_time";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}