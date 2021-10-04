package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysUserChatRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/10/3
 */
public interface SysUserChatRelationMapper extends BaseMapper<SysUserChatRelation> {
    int batchInsert(@Param("list") List<SysUserChatRelation> list);

    int insertOrUpdate(SysUserChatRelation record);

    int insertOrUpdateSelective(SysUserChatRelation record);
}