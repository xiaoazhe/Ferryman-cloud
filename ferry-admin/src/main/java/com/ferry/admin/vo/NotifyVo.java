package com.ferry.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ferry.server.admin.entity.SysNotify;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NotifyVo extends SysNotify {

    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件
     */
    private String files;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 是否发送短信 1 是  0 否
     */
    private String smsRemind;

    /**
     * 通知通告ID
     */
    private Long notifyId;

    /**
     * 接受人
     */
    private List<String> userIds;

}
