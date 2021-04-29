package com.ferry.web.controller;

import com.ferry.web.domain.FBlog;
import com.ferry.web.mapper.FBlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private FBlogMapper blogMapper;
    @RequestMapping("/getBlogById")
    public FBlog hello() {

        return blogMapper.selectById("062ca3475e8193a4a81853a4bf41a8fa");
    }
}