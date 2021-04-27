package com.ferry.admin.controller;

import com.ferry.admin.entity.SysDept;
import com.ferry.admin.service.SysDeptService;
import com.ferry.core.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@Api(value = "部门管理")
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation(value = "添加部门")
    @PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
    @PostMapping(value="/save")
    public Result save(@RequestBody SysDept record) {
        return Result.ok(sysDeptService.save(record));
    }

    @ApiOperation(value = "删除部门")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <SysDept> records) {
        return Result.ok(sysDeptService.delete(records));
    }

    @ApiOperation(value = "获取部门")
    @PreAuthorize("hasAuthority('sys:dept:view')")
    @GetMapping(value="/findTree")
    public Result findTree() {
        return Result.ok(sysDeptService.findTree());
    }

}
