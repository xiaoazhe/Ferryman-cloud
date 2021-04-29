package com.ferry.consumer.testcontroller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "Ferry-web", fallback = MangoProducerHystrix.class)
public interface MangoProducerService {

    @RequestMapping("/getBlogById")
    public String hello();
    
}
