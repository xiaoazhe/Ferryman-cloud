package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysChatRecord;
import java.util.List;
import java.util.Map;

import com.ferry.server.admin.vo.ImChatRecordVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/10/3
 */
public interface SysChatRecordMapper extends BaseMapper<SysChatRecord> {
    int updateBatch(List<SysChatRecord> list);

    int batchInsert(@Param("list") List<SysChatRecord> list);

    int insertOrUpdate(SysChatRecord record);

    int insertOrUpdateSelective(SysChatRecord record);

    /**
     * 查询聊天列表
     *
     * @param imChatRecordVo
     * @return
     */
    List<ImChatRecordVo> selectRecordList(@Param("vo") ImChatRecordVo imChatRecordVo);

    /**
     * 保存聊天记录和用户关系
     *
     * @param imChatRecordVo
     * @return
     */
    boolean saveUserImRelation(@Param("vo") ImChatRecordVo imChatRecordVo);

    /**
     * 查询消息记录
     *
     * @param imChatRecordVo
     * @return
     */
    List<ImChatRecordVo> selectRecordInfoList(@Param("vo") ImChatRecordVo imChatRecordVo);

    /**
     * 查询用户消息记录数量
     *
     * @param imChatRecordVo
     * @return
     */
    Map <String, Object> getUnReadImChatRecordCount(@Param("vo") ImChatRecordVo imChatRecordVo);

    /**
     * 更新聊天记录
     *
     * @param imChatRecordVo
     * @return
     */
    int updateRecord(@Param("vo") ImChatRecordVo imChatRecordVo);
}