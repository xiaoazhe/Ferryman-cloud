package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    SysRole selectByPrimaryKey(Long roleId);

    List <SysRole> findPageByName(@Param(value = "name") String name);

    List<SysRole> findByName(@Param(value = "name") String name);
}