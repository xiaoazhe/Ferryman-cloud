package com.ferry.admin.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.ferry.admin.constant.HandleType;
import com.ferry.common.utils.UuidUtil;
import com.ferry.server.admin.entity.SysChatRecord;
import com.ferry.server.admin.mapper.SysChatRecordMapper;
import com.ferry.server.admin.vo.ImChatRecordVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 描述：点对点单聊 处理器
 */
@HandleType("single")
@Component
public class ChatSingleHandler extends AbstractWebSocketHandler {

    private final SysChatRecordMapper chatRecordMapper;

    public ChatSingleHandler(SysChatRecordMapper chatRecordMapper) {
        this.chatRecordMapper = chatRecordMapper;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean saveOrUpdateChatList(String event) {
        boolean flag = false;
        ImChatRecordVo imChatRecordVo = JSONObject.parseObject(event, ImChatRecordVo.class);
        String senderId = imChatRecordVo.getSenderId();
        String receiverId = imChatRecordVo.getReceiverId();

        //1.插入聊天记录
        SysChatRecord imChatRecord = new SysChatRecord();
        imChatRecord.setId(UuidUtil.randomUUID());
        imChatRecord.setSenderId(senderId);
        imChatRecord.setSendTime(new Date());
        imChatRecord.setMsgContent(imChatRecordVo.getMsgContent());
        if (chatRecordMapper.insert(imChatRecord) != -1 ) {
            imChatRecordVo.setChatId(imChatRecord.getId());
            imChatRecordVo.setIsRead(0);
            //2.分别新增根据消息发送人、消息接收人与聊天记录关系
            flag = chatRecordMapper.saveUserImRelation(imChatRecordVo);
            imChatRecordVo.setSenderId(receiverId);
            imChatRecordVo.setReceiverId(senderId);
            flag = chatRecordMapper.saveUserImRelation(imChatRecordVo);
        }

        return flag;
    }
}
