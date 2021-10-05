package com.ferry.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天记录Vo
 * @Author: 摆渡人
 * @Date: 2021/10/5
 */
@Data
@ApiModel(value = "ImChatRecord对象Vo", description = "聊天记录Vo")
public class ImChatRecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "聊天列表id")
    private String chatId;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "发送时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "最新消息")
    private String msgContent;

    @ApiModelProperty(value = "未读消息数量")
    private Integer unReadCount;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "消息发送人id")
    private String senderId;

    @ApiModelProperty(value = "消息接收人id")
    private String receiverId;

    @ApiModelProperty(value = "是否已读")
    private int isRead;

    @ApiModelProperty(value = "是否删除")
    private int isDeleted;
}