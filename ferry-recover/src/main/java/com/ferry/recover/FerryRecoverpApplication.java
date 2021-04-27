package com.ferry.recover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 备份服务
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@EnableDiscoveryClient
@SpringBootApplication()
public class FerryRecoverpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerryRecoverpApplication.class, args);
	}
}