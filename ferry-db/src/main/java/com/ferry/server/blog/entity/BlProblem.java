package com.ferry.server.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
@Data
@TableName(value = "bl_problem")
public class BlProblem implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 用户ID
     */
    @TableField(value = "userid")
    private String userid;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 浏览量
     */
    @TableField(value = "visits")
    private Long visits;

    /**
     * 点赞数
     */
    @TableField(value = "thumbup")
    private Long thumbup;

    /**
     * 回复数
     */
    @TableField(value = "reply")
    private Long reply;

    /**
     * 是否解决
     */
    @TableField(value = "solve")
    private String solve;

    /**
     * 回复人昵称
     */
    @TableField(value = "replyname")
    private String replyname;

    /**
     * 回复日期
     */
    @TableField(value = "replytime")
    private Date replytime;

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

    @TableField(exist = false)
    private List <BlLabel> labelList;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_CONTENT = "content";

    public static final String COL_CREATETIME = "create_time";

    public static final String COL_UPDATETIME = "update_time";

    public static final String COL_USERID = "userid";

    public static final String COL_NICKNAME = "nickname";

    public static final String COL_VISITS = "visits";

    public static final String COL_THUMBUP = "thumbup";

    public static final String COL_REPLY = "reply";

    public static final String COL_SOLVE = "solve";

    public static final String COL_REPLYNAME = "replyname";

    public static final String COL_REPLYTIME = "replytime";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}