package com.ferry.consumer.controller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.interceptor.JwtUtil;
import com.ferry.consumer.service.BlogService;
import com.ferry.consumer.service.impl.BlogDealService;
import com.ferry.server.blog.entity.BlBlog;
import com.ferry.server.blog.mapper.BlUserMapper;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogDealService blogDealService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BlUserMapper userMapper;

    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        Result result = blogService.findPage(pageRequest);
        return result;
    }

    @PostMapping(value="/getBlogById")
    public Result getBlogById(@RequestParam(value = "id") String id) {
        Result result = blogService.getBlogById(id);
        return result;
    }

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
