package com.ferry.core.file.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractDO implements Serializable {
    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     */
    private static final long serialVersionUID = 5088697673359856350L;

    private Long id;

    private Date createTime;
    private Date updateTime;

}