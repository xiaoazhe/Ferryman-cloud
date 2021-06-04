package com.ferry.consumer.service;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.impl.BlogServiceImpl;
import com.ferry.consumer.service.impl.TypeServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/3
 */
@FeignClient(name = "Ferry-web", fallback = TypeServiceImpl.class)
public interface TypeService {

    @RequestMapping("/type/findAll")
    public Result findAll();

    @RequestMapping("/type/findBlogByTypeId")
    public Result findBlogByTypeId(PageRequest pageRequest);
}
