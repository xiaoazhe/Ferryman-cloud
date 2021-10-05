package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.service.ChatRecordService;
import com.ferry.server.admin.entity.SysChatRecord;
import com.ferry.server.admin.mapper.SysChatRecordMapper;
import com.ferry.server.admin.vo.ImChatRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: 摆渡人
 * @Date: 2021/10/5
 */
public class ChatRecordServiceImpl extends ServiceImpl <SysChatRecordMapper, SysChatRecord> implements ChatRecordService {

    @Autowired
    private SysChatRecordMapper imChatRecordMapper;

    @Override
    public List <ImChatRecordVo> selectRecordList(ImChatRecordVo imChatRecordVo) {
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