package com.ferry.file;

import com.ferry.common.utils.IdWorker;
import com.ferry.file.interceptor.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
@EnableDiscoveryClient
@SpringBootApplication()
public class FerryFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerryFileApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
}

