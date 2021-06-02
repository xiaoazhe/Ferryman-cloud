package com.ferry.web.controller;


import com.ferry.server.blog.entity.BlBlog;
import com.ferry.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private BlogService blogService;
    @RequestMapping("/getBlogById")
    public BlBlog blBlog() {
        return blogService.selectById("062ca3475e8193a4a81853a4bf41a8a1");
    }
    @RequestMapping("/hello")
    public String hello() {
        return "hello Mango 2!";
    }
}