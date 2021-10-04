package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysUserGroupInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/10/3
 */
public interface SysUserGroupInfoMapper extends BaseMapper<SysUserGroupInfo> {
    int updateBatch(List<SysUserGroupInfo> list);

    int batchInsert(@Param("list") List<SysUserGroupInfo> list);

    int insertOrUpdate(SysUserGroupInfo record);

    int insertOrUpdateSelective(SysUserGroupInfo record);
}