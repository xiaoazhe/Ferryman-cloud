package com.ferry.blog.controller;

import com.ferry.blog.entity.BlBlog;
import com.ferry.blog.service.BlogService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
@Api(tags = "博客模块")
@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:blog:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(blogService.findPage(pageRequest));
    }

    @ApiOperation(value = "添加博客")
    @PreAuthorize("hasAuthority('sys:blog:add') AND hasAuthority('sys:blog:edit')")
    @PostMapping(value="/save")
    public Result save(@RequestBody BlBlog blog) {
        return Result.ok(blogService.saveBlog(blog));
    }

    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('sys:blog:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <BlBlog> blogs) {
        return Result.ok(blogService.removeTypes(blogs));
    }

}
