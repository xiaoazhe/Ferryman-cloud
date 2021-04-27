package com.ferry.admin.controller;

import com.ferry.admin.constant.SysConstants;
import com.ferry.admin.entity.SysRole;
import com.ferry.admin.entity.SysRoleMenu;
import com.ferry.admin.service.SysRoleService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
@Api(value = "角色管理")
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "添加角色")
    @PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
    @PostMapping(value="/save")
    public Result save(@RequestBody SysRole record) {
        SysRole role = sysRoleService.findById(record.getId());
        if(role != null) {
            if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
                return Result.error("超级管理员不允许修改!");
            }
        }
        // 新增角色
        if((record.getId() == null || record.getId() ==0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
            return Result.error("角色名已存在!");
        }
        return Result.ok(sysRoleService.save(record));
    }

    @ApiOperation(value = "删除角色")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <SysRole> records) {
        return Result.ok(sysRoleService.delete(records));
    }

    @ApiOperation(value = "查询角色列表")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(sysRoleService.findPage(pageRequest));
    }

    @ApiOperation(value = "获取所有")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findAll")
    public Result findAll() {
        return Result.ok(sysRoleService.findAll());
    }

    @ApiOperation(value = "获取角色菜单")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findRoleMenus")
    public Result findRoleMenus(@RequestParam Long roleId) {
        return Result.ok(sysRoleService.findRoleMenus(roleId));
    }

    @ApiOperation(value = "保存角色菜单")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/saveRoleMenus")
    public Result saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        for(SysRoleMenu record:records) {
            SysRole sysRole = sysRoleService.selectByPrimaryKey(record.getRoleId());
            if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                // 如果是超级管理员，不允许修改
                return Result.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return Result.ok(sysRoleService.saveRoleMenus(records));
    }
}
