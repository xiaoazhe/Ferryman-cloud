package com.ferry.server.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
@Data
@TableName(value = "bl_pro_label")
@EqualsAndHashCode(callSuper = false)
public class BlProLabel implements Serializable {
    /**
     * 问题ID
     */
    @TableId(value = "problemid", type = IdType.INPUT)
    private String problemid;

    /**
     * 标签ID
     */
    @TableId(value = "labelid", type = IdType.INPUT)
    private String labelid;

    private static final long serialVersionUID = 1L;

    public static final String COL_PROBLEMID = "problemid";

    public static final String COL_LABELID = "labelid";
}