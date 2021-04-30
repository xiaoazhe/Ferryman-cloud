package com.ferry.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 *
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
@EnableDiscoveryClient
@MapperScan("com.ferry.admin.mapper")
@SpringBootApplication()
public class FerryAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerryAdminApplication.class, args);
	}

}

