package com.ferry.consumer.service.impl;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.BlogService;
import com.ferry.consumer.service.TypeService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@Component
public class TypeServiceImpl implements TypeService {

    @RequestMapping("/type/findAll")
    public Result findAll() {
        return Result.error("抱歉,没有获取到");
    }

}
