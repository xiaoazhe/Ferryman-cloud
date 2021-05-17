package com.ferry.blog.controller;

import com.ferry.blog.entity.BlBlog;
import com.ferry.blog.entity.BlType;
import com.ferry.blog.service.TypeService;
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
 * @Date: 2021/5/7
 */
@Api(tags = "分类模块")
@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:type:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(typeService.findPage(pageRequest));
    }

    @ApiOperation(value = "添加分类")
    @PreAuthorize("hasAuthority('sys:type:add') AND hasAuthority('sys:type:edit')")
    @PostMapping(value="/save")
    public Result save(@RequestBody BlType type) {
        return Result.ok(typeService.saveType(type));
    }

    @ApiOperation(value = "删除类型")
    @PreAuthorize("hasAuthority('sys:type:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <BlType> types) {
        return Result.ok(typeService.removeTypes(types));
    }

    @ApiOperation(value = "查询全部")
    @PreAuthorize("hasAuthority('sys:type:view')")
    @GetMapping(value="/findAll")
    public Result findAll() {
        return Result.ok(typeService.findAll());
    }

    @ApiOperation(value = "根据id查询")
    @PreAuthorize("hasAuthority('sys:type:view')")
    @GetMapping(value="/findById/{blogId}")
    public Result findById(@PathVariable String blogId) {
        return Result.ok(typeService.findById(blogId));
    }
}
