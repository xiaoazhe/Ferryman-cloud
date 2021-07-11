package com.ferry.blog.amqp;

import com.ferry.blog.service.UserService;
import com.ferry.server.blog.entity.BlUser;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * @Author: 摆渡人
 * @Date: 2021/7/11
 * 消费队列
 */

@Component
@RabbitListener(queues = "repeatTradeQueue")
public class RepeatTradeReceiver {

    @Autowired
    private UserService userService;

    @RabbitHandler
    public void process(String msg) {
        try {
            System.out.println("repeatTradeQueue 接收时间:" + LocalDateTime.now().toString() + " 接收内容:" + msg);
            BlUser user = userService.getById(msg);
            user.setStatus(0);
            userService.updateById(user);
        } catch (Exception e) {
            throw new RuntimeException("删除异常");
        }
    }
}
