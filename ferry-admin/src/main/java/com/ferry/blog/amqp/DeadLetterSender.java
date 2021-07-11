package com.ferry.blog.amqp;

import com.ferry.blog.service.UserService;
import com.ferry.server.blog.entity.BlUser;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

/**
 * @Author: 摆渡人
 * @Date: 2021/7/11
 */

@Component
public class DeadLetterSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private UserService userService;

    public void send(String msg) {
        System.out.println("DeadLetterSender 发送时间:" + LocalDateTime.now().toString() + " msg内容：" + msg);
        rabbitTemplate.convertAndSend("deadLetterQueue", msg);
    }

    /**
     * 消息发送 加入队列
     * @param msg
     * @param times
     */
    public void send(String msg, long times) {
        System.out.println("DeadLetterSender 发送时间:" + LocalDateTime.now().toString() + " msg内容：" + msg);
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        try {
            BlUser user = userService.getById(msg);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setFrom(from);
            message.setTo(user.getEmail());
            message.setSubject("FerryMan系统提醒：");
            message.setText("<h1>该用户账号因为特殊原因将于三天后清除，有问题请联系系统管理员，或在系统留言板留言。</h1>", true);
            mailSender.send(mimeMessage);
            } catch (Exception e) {
            throw new RuntimeException("短信发送异常");
        }
        rabbitTemplate.convertAndSend("deadLetterQueue", (Object)msg, processor);
    }

}