package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findByUserName(@Param(value = "userName") String userName);

    List<SysMenu> findRoleMenus(@Param(value = "roleId") Long roleId);
}