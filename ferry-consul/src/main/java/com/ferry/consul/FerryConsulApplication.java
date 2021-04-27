package com.ferry.consul;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class FerryConsulApplication {
    public static void main(String[] args) {
        SpringApplication.run(FerryConsulApplication.class, args);
    }

}
