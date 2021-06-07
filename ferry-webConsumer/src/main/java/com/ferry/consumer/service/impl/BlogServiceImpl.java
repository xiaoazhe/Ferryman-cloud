package com.ferry.consumer.service.impl;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.BlogService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@Component
public class BlogServiceImpl implements BlogService {

    @RequestMapping("/blog/findPage")
    public Result findPage(PageRequest pageRequest) {
        return Result.error();
    }

    @RequestMapping("/blog/getBlogById")
    public Result getBlogById(String id) {
        return Result.error();
    }

    @PostMapping("/blog/hotBlog")
    public Result hotBlog() {
        return Result.error();
    }

    @RequestMapping("/getBlogById")
    public String hello() {
        return "抱歉,没有获取到";
    }
}
