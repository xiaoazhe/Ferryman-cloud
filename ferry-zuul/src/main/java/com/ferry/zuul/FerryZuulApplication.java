package com.ferry.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@EnableZuulProxy
@SpringBootApplication
public class FerryZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(FerryZuulApplication.class, args);
    }

}
