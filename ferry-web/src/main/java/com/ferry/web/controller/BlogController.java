package com.ferry.web.controller;

import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.server.blog.entity.BlBlog;
import com.ferry.web.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "博客模块")
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "分页查询")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(blogService.findPage(pageRequest));
    }

    @RequestMapping("/getBlogById")
    public BlBlog blBlog() {
        return blogService.selectById("11e0cace2148383e201439a682432d91");
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello Mango !";
    }
}