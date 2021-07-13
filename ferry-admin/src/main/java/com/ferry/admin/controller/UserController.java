package com.ferry.admin.controller;

import com.ferry.admin.constant.SysConstants;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.admin.service.SysUserService;
import com.ferry.admin.util.PasswordUtils;
import com.ferry.admin.util.SecurityUtils;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    SysUserService userService;

    @ApiOperation(value = "添加用户")
    @PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
    @PostMapping(value="/save")
    public Result save(@RequestBody SysUser record) {
        SysUser user = userService.findById(record.getId());
        if(user != null) {
            if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
                return Result.error("超级管理员不允许修改!");
            }
        }
        if(record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if(user == null) {
                // 新增用户
                if(userService.findByName(record.getName()) != null) {
                    return Result.error("用户名已存在!");
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                // 修改用户, 且修改了密码
                if(!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        return Result.ok(userService.save(record));
    }

    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <SysUser> records) {
        for(SysUser record:records) {
            SysUser sysUser = userService.findById(record.getId());
            if(sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
                return Result.error("超级管理员不允许删除!");
            }
        }
        return Result.ok(userService.delete(records));
    }


    @ApiOperation(value = "查找权限")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value="/findPermissions")
    public Result findPermissions(@RequestParam String name) {
        return Result.ok(userService.findPermissions(name));
    }

    @ApiOperation(value = "查找用户角色")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value="/findUserRoles")
    public Result findUserRoles(@RequestParam Long userId) {
        return Result.ok(userService.findUserRoles(userId));
    }

    @ApiOperation(value = "根据名称获取用户")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value="/findByName")
    public Result findByUserName(@RequestParam String name) {
        return Result.ok(userService.findByName(name));
    }

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(userService.findPage(pageRequest));
    }

    @ApiOperation(value = "导出")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping(value="/exportUserExcelFile")
    public Result exportUserExcelFile(@RequestBody PageRequest pageRequest) {
        return Result.ok(userService.createUserExcelFile(pageRequest));
    }

    @ApiOperation(value = "更新密码")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @GetMapping(value="/updatePassword")
    public Result updatePassword(@RequestParam String password, @RequestParam String newPassword) {
        SysUser user = userService.findByName(SecurityUtils.getUsername());
        if(user == null) {
            Result.error("用户不存在!");
        }
        if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
            return Result.error("超级管理员不允许修改 你!");
        }
        if(!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return Result.error("原密码不正确!");
        }
        user.setPassword(PasswordUtils.encode(newPassword, user.getSalt()));
        return Result.ok(userService.save(user));
    }

    @ApiOperation(value = "获取登陆用户")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value="/getLoginUser")
    public Result getLoginUser() {
        return Result.ok(userService.findByName(SecurityUtils.getUsername()));
    }
}
