package com.ferry.web.controller;

import com.ferry.common.utils.StringUtils;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.web.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "id查询")
    @PostMapping("/getBlogById")
    public Result blBlog(@RequestBody String id) {
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("id不能为空");
        }
        Result result = blogService.selectById(id);
        return result;
    }

    @ApiOperation(value = "热门查询")
    @PostMapping("/hotBlog")
    public Result hotBlog() {
        Result result = blogService.hotBlog();
        return result;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello Mango !";
    }
}