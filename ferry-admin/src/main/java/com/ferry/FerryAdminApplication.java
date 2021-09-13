package com.ferry;

import com.ferry.common.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;


/**
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
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }

    // 用于死信队列转发的交换机
    @Bean
    DirectExchange exchange() {
        return new DirectExchange("exchange");
    }

    // 用于延时消费的队列
    @Bean
    public Queue repeatTradeQueue() {
        Queue queue = new Queue("repeatTradeQueue", true, false, false);
        return queue;
    }

    // 绑定交换机并指定routing key
    @Bean
    public Binding repeatTradeBinding() {
        return BindingBuilder.bind(repeatTradeQueue()).to(exchange()).with("repeatTradeQueue");
    }

    // 配置死信队列
    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "exchange");
        args.put("x-dead-letter-routing-key", "repeatTradeQueue");
        return new Queue("deadLetterQueue", true, false, false, args);
    }
}

