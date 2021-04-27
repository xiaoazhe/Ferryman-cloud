package com.ferry.admin.controller;

import com.ferry.admin.entity.SysConfig;
import com.ferry.admin.service.SysConfigService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统配置
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@Api(value = "系统配置")
@RestController
@RequestMapping("config")
public class ConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation(value = "添加配置")
    @PreAuthorize("hasAuthority('sys:config:add') AND hasAuthority('sys:config:edit')")
    @PostMapping(value="/save")
    public Result save(@RequestBody SysConfig record) {
        return Result.ok(sysConfigService.save(record));
    }

    @ApiOperation(value = "删除配置")
    @PreAuthorize("hasAuthority('sys:config:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <SysConfig> records) {
        return Result.ok(sysConfigService.delete(records));
    }

    @ApiOperation(value = "查询配置")
    @PreAuthorize("hasAuthority('sys:config:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(sysConfigService.findPage(pageRequest));
    }

    @ApiOperation(value = "通过标签查找")
    @PreAuthorize("hasAuthority('sys:config:view')")
    @GetMapping(value="/findByLable")
    public Result findByLable(@RequestParam String lable) {
        return Result.ok(sysConfigService.findByLable(lable));
    }
}
