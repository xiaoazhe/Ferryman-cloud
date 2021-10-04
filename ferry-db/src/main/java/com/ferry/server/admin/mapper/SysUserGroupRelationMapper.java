package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysUserGroupRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/10/3
 */
public interface SysUserGroupRelationMapper extends BaseMapper<SysUserGroupRelation> {
    int updateBatch(List<SysUserGroupRelation> list);

    int batchInsert(@Param("list") List<SysUserGroupRelation> list);

    int insertOrUpdate(SysUserGroupRelation record);

    int insertOrUpdateSelective(SysUserGroupRelation record);
}