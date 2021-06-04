package com.ferry.consumer.service;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.impl.BlogServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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

    @RequestMapping("/getBlogById")
    public String hello();
}
