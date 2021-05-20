package com.ferry.admin.controller;

import com.ferry.server.admin.entity.SysMenu;
import com.ferry.admin.service.SysMenuService;
import com.ferry.core.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 菜单管理
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
@Api(tags = "菜单模块")
@RestController
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "添加菜单")
    @PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
    @PostMapping(value="/save")
    public Result save(@RequestBody SysMenu record) {
        return Result.ok(sysMenuService.save(record));
    }

    @ApiOperation(value = "删除菜单")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <SysMenu> records) {
        return Result.ok(sysMenuService.delete(records));
    }

    @ApiOperation(value = "根据登录人员获取左侧菜单")
    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findNavTree")
    public Result findNavTree(@RequestParam String userName) {
        return Result.ok(sysMenuService.findTree(userName, 1));
    }

    @ApiOperation(value = "获取菜单")
    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findMenuTree")
    public Result findMenuTree() {
        return Result.ok(sysMenuService.findTree(null, 0));
    }

}
