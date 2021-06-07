package com.ferry.consumer.controller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/hello")
    public String call() {
        // 像调用本地服务一样
        return blogService.hello();
    }
}
