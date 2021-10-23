package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.service.ChatRecordService;
import com.ferry.server.admin.entity.SysChatRecord;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.server.admin.entity.SysUserChatRelation;
import com.ferry.server.admin.mapper.SysChatRecordMapper;
import com.ferry.server.admin.mapper.SysUserChatRelationMapper;
import com.ferry.server.admin.mapper.SysUserMapper;
import com.ferry.server.admin.vo.ImChatRecordVo;
import com.ferry.server.blog.entity.BlProLabel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 摆渡人
 * @Date: 2021/10/5
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl <SysChatRecordMapper, SysChatRecord> implements ChatRecordService {

    @Autowired
    private SysChatRecordMapper imChatRecordMapper;

    @Autowired
    private SysUserChatRelationMapper userChatRelationMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List <ImChatRecordVo> selectRecordList(ImChatRecordVo imChatRecordVo) {
//        imChatRecordVo.setSenderId("137947366632550162");
        if (StringUtils.isBlank(imChatRecordVo.getSenderId())) {
            throw new RuntimeException("null");
        }
        QueryWrapper<SysUserChatRelation> queryWrapper = new QueryWrapper();
        queryWrapper.eq(SysUserChatRelation.COL_SENDER_ID, imChatRecordVo.getSenderId());
        List<String> chatId = userChatRelationMapper.selectList(queryWrapper).stream().
                map(SysUserChatRelation::getChatId).collect(Collectors.toList());
        QueryWrapper<SysChatRecord> chatRecordQueryWrapper = new QueryWrapper();
        chatRecordQueryWrapper.in(SysChatRecord.COL_ID, chatId);
        List <SysChatRecord> sysChatRecords = imChatRecordMapper.selectList(chatRecordQueryWrapper);
        List <ImChatRecordVo> chatRecordVos = new ArrayList<>();
        SysUser user = sysUserMapper.selectById(imChatRecordVo.getSenderId());
        for (SysChatRecord sysChatRecord : sysChatRecords) {
            ImChatRecordVo chatRecordVo = new ImChatRecordVo();
            chatRecordVo.setChatId(sysChatRecord.getId());
            chatRecordVo.setMsgContent(sysChatRecord.getMsgContent());
            chatRecordVo.setSenderId(imChatRecordVo.getSenderId());
            chatRecordVo.setAvatar(user.getAvatar());
            chatRecordVo.setEmail(user.getEmail());
            chatRecordVo.setName(user.getName());
            chatRecordVos.add(chatRecordVo);
        }
        return imChatRecordMapper.selectRecordList(imChatRecordVo);
    }

    @Override
    public boolean saveUserImRelation(ImChatRecordVo imChatRecordVo) {
        return imChatRecordMapper.saveUserImRelation(imChatRecordVo);
    }

    /**
     * 查询聊天记录详情
     *
     * @param imChatRecordVo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public List<ImChatRecordVo> selectRecordInfoList(ImChatRecordVo imChatRecordVo) {
        List<ImChatRecordVo> imChatRecordVos = imChatRecordMapper.selectRecordInfoList(imChatRecordVo);
        return imChatRecordVos;
    }

    /**
     * 获取未读消息
     *
     * @param imChatRecordVo
     * @return
     */
    @Override
    public Map <String, Object> getUnReadImChatRecordCount(ImChatRecordVo imChatRecordVo) {
        return imChatRecordMapper.getUnReadImChatRecordCount(imChatRecordVo);
    }

    /**
     * 更新聊天记录为已读
     *
     * @param imChatRecordVo
     * @return
     */
    @Override
    public boolean imChatRecordUpdate(ImChatRecordVo imChatRecordVo) {
        return imChatRecordMapper.updateRecord(imChatRecordVo) > 0;
    }

    /**
     * 删除聊天记录
     *
     * @param imChatRecordVo
     * @return
     */
    @Override
    public boolean imChatRecordDelete(ImChatRecordVo imChatRecordVo) {
        return imChatRecordMapper.updateRecord(imChatRecordVo) > 0;
    }
}