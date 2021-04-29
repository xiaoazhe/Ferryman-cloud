package com.ferry.consumer.service;

import com.ferry.consumer.service.impl.BlogServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@FeignClient(name = "Ferry-web", fallback = BlogServiceImpl.class)
public interface BlogService {

    @RequestMapping("/getBlogById")
    public String hello();


}
