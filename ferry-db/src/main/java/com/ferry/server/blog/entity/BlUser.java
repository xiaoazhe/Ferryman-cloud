package com.ferry.server.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/6/5
 */
@Data
@TableName(value = "bl_user")
@EqualsAndHashCode(callSuper = false)
public class BlUser implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 手机号码
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 出生年月日
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * E-Mail
     */
    @TableField(value = "email")
    private String email;

    /**
     * 注册日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "regdate")
    private Date regdate;

    /**
     * 修改日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 修改日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后登陆日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "lastdate")
    private Date lastdate;

    /**
     * 在线时长（分钟）
     */
    @TableField(value = "online")
    private Long online;

    /**
     * 兴趣
     */
    @TableField(value = "interest")
    private String interest;

    /**
     * 个性
     */
    @TableField(value = "personality")
    private String personality;

    /**
     * 粉丝数
     */
    @TableField(value = "fanscount")
    private Integer fanscount;

    /**
     * 关注数
     */
    @TableField(value = "followcount")
    private Integer followcount;

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

    @TableField(value = "status")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_PASSWORD = "password";

    public static final String COL_NICKNAME = "nickname";

    public static final String COL_SEX = "sex";

    public static final String COL_BIRTHDAY = "birthday";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_EMAIL = "email";

    public static final String COL_REGDATE = "regdate";

    public static final String COL_UPDATEDATE = "update_time";

    public static final String COL_CREATWDATE = "create_time";

    public static final String COL_LASTDATE = "lastdate";

    public static final String COL_ONLINE = "online";

    public static final String COL_INTEREST = "interest";

    public static final String COL_PERSONALITY = "personality";

    public static final String COL_FANSCOUNT = "fanscount";

    public static final String COL_FOLLOWCOUNT = "followcount";
}