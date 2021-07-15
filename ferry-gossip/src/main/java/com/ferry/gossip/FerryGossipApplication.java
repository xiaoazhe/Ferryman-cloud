package com.ferry.gossip;


import com.ferry.common.utils.IdWorker;
import com.ferry.gossip.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/11
 */
@EnableDiscoveryClient
@EnableMongoRepositories(value= "com.ferry.server.blog.db")
@SpringBootApplication
public class FerryGossipApplication {

    public static void main(String[] args) {
        SpringApplication.run(FerryGossipApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}