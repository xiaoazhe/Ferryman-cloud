package com.ferry.consumer.controller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.BlogService;
import com.ferry.consumer.service.impl.BlogDealService;
import com.ferry.server.blog.entity.BlBlog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */

@Api(tags = "博客")
@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogDealService blogDealService;

    @ApiOperation(value = "分页查询")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        Result result = blogService.findPage(pageRequest);
        return result;
    }

    @ApiOperation(value = "根据ID获取")
    @PostMapping(value="/getBlogById")
    public Result getBlogById(@RequestParam(value = "id") String id) {
        Result result = blogService.getBlogById(id);
        return result;
    }

    @ApiOperation(value = "热门推荐")
    @PostMapping(value="/hotBlog")
    public Result hotBlog() {
        return blogService.hotBlog();
    }

    @ApiOperation(value = "用户添加博客")
    @PostMapping("/saveBlog")
    public Result saveBlog(@RequestBody BlBlog blBlog, HttpServletRequest request) {
        return blogDealService.saveBlog(blBlog);
    }

    @RequestMapping("/hello")
    public String call() {
        // 像调用本地服务一样
        return blogService.hello();
    }
}
