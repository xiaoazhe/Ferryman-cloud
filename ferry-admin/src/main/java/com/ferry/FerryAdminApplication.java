package com.ferry;

import com.ferry.common.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


/**
 *
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
@EnableDiscoveryClient
@MapperScan({"com.ferry.core.*.mapper", "com.ferry.server.*.mapper"})
@SpringBootApplication()
public class FerryAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerryAdminApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

}

