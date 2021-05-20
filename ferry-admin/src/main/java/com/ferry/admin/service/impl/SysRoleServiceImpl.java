package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.constant.SysConstants;
import com.ferry.server.admin.entity.SysMenu;
import com.ferry.server.admin.entity.SysRole;
import com.ferry.server.admin.entity.SysRoleMenu;
import com.ferry.server.admin.mapper.SysMenuMapper;
import com.ferry.server.admin.mapper.SysRoleMapper;
import com.ferry.server.admin.mapper.SysRoleMenuMapper;
import com.ferry.admin.service.SysRoleService;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl <SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public boolean save(SysRole record) {
        if(record.getId() == null || record.getId() == 0) {
            sysRoleMapper.insert(record);
            return true;
        }
        sysRoleMapper.updateById(record);
        return true;
    }

    @Override
    public int delete(SysRole record) {
        return sysRoleMapper.deleteById(record.getId());
    }

    @Override
    public int delete(List <SysRole> records) {
        for(SysRole record:records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.selectById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object name = pageRequest.getParamValue("name");
        Page <SysRole> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like(!Objects.isNull(name),"name", name);
        Page<SysRole> userIPage = sysRoleMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(userIPage);
        return pageResult;
    }

    @Override
    public List<SysRole> findAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("id", 0);
        return sysRoleMapper.selectList(queryWrapper);
    }

    public SysRoleMapper getSysRoleMapper() {
        return sysRoleMapper;
    }

    public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public List<SysMenu> findRoleMenus(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            // 如果是超级管理员，返回全部
            QueryWrapper queryWrapper = new QueryWrapper <>().ne("id", 0);
            return sysMenuMapper.selectList(queryWrapper);
        }
        return sysMenuMapper.findRoleMenus(roleId);
    }

    @Transactional
    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {
        if(records == null || records.isEmpty()) {
            return 1;
        }
        Long roleId = records.get(0).getRoleId();
        sysRoleMenuMapper.deleteById(roleId);
        for(SysRoleMenu record:records) {
            sysRoleMenuMapper.insert(record);
        }
        return 1;
    }

    @Override
    public List<SysRole> findByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", name);
        return sysRoleMapper.selectList(queryWrapper);
    }

    @Override
    public SysRole selectByPrimaryKey(Long roleId) {
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }

}
