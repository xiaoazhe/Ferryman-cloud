package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.server.admin.vo.ImChatRecordVo;
import com.ferry.server.admin.entity.SysChatRecord;

import java.util.List;
import java.util.Map;

/**
 * @Author: 摆渡人
 * @Date: 2021/10/5
 */
public interface ChatRecordService extends IService <SysChatRecord> {

    /**
     * 查询聊天列表
     *
     * @param imChatRecordVo
     * @return
     */
    List <ImChatRecordVo> selectRecordList(ImChatRecordVo imChatRecordVo);

    /**
     * 保存用户和聊天记录关系
     *
     * @param imChatRecordVo
     * @return
     */
    boolean saveUserImRelation(ImChatRecordVo imChatRecordVo);

    /**
     * 查询聊天记录详情
     *
     * @param imChatRecordVo
     * @return
     */
    List<ImChatRecordVo> selectRecordInfoList(ImChatRecordVo imChatRecordVo);

    /**
     * 查询用户的消息记录数量
     *
     * @param imChatRecordVo
     * @return
     */
    Map <String, Object> getUnReadImChatRecordCount(ImChatRecordVo imChatRecordVo);

    /**
     * 更新消息为已读
     *
     * @param imChatRecordVo
     * @return
     */
    boolean imChatRecordUpdate(ImChatRecordVo imChatRecordVo);

    /**
     * 删除聊天记录
     *
     * @param imChatRecordVo
     * @return
     */
    boolean imChatRecordDelete(ImChatRecordVo imChatRecordVo);
}
