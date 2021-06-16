package com.ferry.consumer.service;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.impl.BlogServiceImpl;
import com.ferry.server.blog.entity.BlBlog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@FeignClient(name = "Ferry-web", fallback = BlogServiceImpl.class)
public interface BlogService {

    @RequestMapping("/blog/findPage")
    public Result findPage(PageRequest pageRequest);

    @RequestMapping("/blog/getBlogById")
    public Result getBlogById(String id);

    @PostMapping("/blog/hotBlog")
    Result hotBlog();

    @PostMapping("/blog/saveBlog")
    Result saveBlog(BlBlog blBlog);

    @RequestMapping("/getBlogById")
    public String hello();

    @PostMapping("/blog/findUserPage/{userId}")
    Result findUserPage(@PathVariable String userId, PageRequest pageRequest);

    @GetMapping("/blog/delete/{id}")
    Result deleteById(@PathVariable String id);
}
