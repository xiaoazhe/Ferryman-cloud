package com.ferry.core.file.entity.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ferry.core.file.entity.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlFile {

    private String id;
    /**
     * 上传用户
     */
    @TableField(value = "user_id")
    private Long userId;

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
    @TableField(value = "upload_start_time")
    private Date uploadStartTime;

    /**
     * 上传结束时间
     */
    @TableField(value = "upload_end_time")
    private Date uploadEndTime;

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

    private Date createTime;
    private Date updateTime;
}
