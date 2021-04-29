package com.ferry.consumer.service.impl;

import com.ferry.consumer.service.BlogService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@Component
public class BlogServiceImpl implements BlogService {

    @RequestMapping("/getBlogById")
    public String hello() {
        return "抱歉,没有获取到";
    }
}
