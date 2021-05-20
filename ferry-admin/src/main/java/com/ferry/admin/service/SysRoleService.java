package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.server.admin.entity.SysMenu;
import com.ferry.server.admin.entity.SysRole;
import com.ferry.server.admin.entity.SysRoleMenu;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
public interface SysRoleService extends IService <SysRole> {

    boolean save(SysRole record);

    int delete(SysRole record);

    int delete(List <SysRole> records);

    SysRole findById(Long id);

    PageResult findPage(PageRequest pageRequest);
    /**
     * 查询全部
     * @return
     */
    List <SysRole> findAll();

    /**
     * 查询角色菜单集合
     * @return
     */
    List<SysMenu> findRoleMenus(Long roleId);

    /**
     * 保存角色菜单
     * @param records
     * @return
     */
    int saveRoleMenus(List<SysRoleMenu> records);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<SysRole> findByName(String name);

    SysRole selectByPrimaryKey(Long roleId);
}
