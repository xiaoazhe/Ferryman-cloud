package com.ferry.admin.controller;

import com.ferry.server.admin.entity.SysDict;
import com.ferry.admin.service.SysDictService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典模块
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@RequestMapping("dict")
@RestController
@Api(tags = "字典管理")
public class DictController {

    @Autowired
    private SysDictService sysDictService;

    @ApiOperation(value = "添加字典")
    @PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
    @PostMapping(value="/save")
    public Result save(@RequestBody SysDict record) {
        return Result.ok(sysDictService.save(record));
    }

    @ApiOperation(value = "删除字典")
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <SysDict> records) {
        return Result.ok(sysDictService.delete(records));
    }

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(sysDictService.findPage(pageRequest));
    }

    @ApiOperation(value = "通过标签查找")
    @PreAuthorize("hasAuthority('sys:dict:view')")
    @GetMapping(value="/findByLable")
    public Result findByLable(@RequestParam String lable) {
        return Result.ok(sysDictService.findByLable(lable));
    }

}
