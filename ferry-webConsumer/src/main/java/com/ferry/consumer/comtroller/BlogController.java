package com.ferry.consumer.comtroller;

import com.ferry.consumer.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blog/hello")
    public String call() {
        // 像调用本地服务一样
        return blogService.hello();
    }
}
