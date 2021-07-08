package com.ferry.blog.controller;

import com.ferry.blog.service.UserService;
import com.ferry.common.enums.StateEnums;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.server.blog.entity.BlUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @Author: 摆渡人
 * @Date: 2021/7/1
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @ApiOperation(value = "获取列表")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping(value = "/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest){
        return Result.ok(userService.findPage(pageRequest));
    }

    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @PostMapping(value = "/findPage")
    public Result delete(@PathVariable Integer id){
        if (id == null) {
            return Result.error();
        }
        try {
            BlUser user = userService.getById(id);
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(from);
//            message.setTo(user.getEmail());
//            message.setSubject("FerryMan系统提醒：");
//            message.setText("该用户账号因为特殊原因清除，有问题请联系系统管理员，或在系统留言板留言");
//            mailSender.send(message);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
            message.setFrom(from);
            message.setTo(user.getEmail());
            message.setSubject("FerryMan系统提醒：");
            message.setText("<h1>该用户账号因为特殊原因将要清除，有问题请联系系统管理员，或在系统留言板留言</h1>", true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        return Result.ok(userService.removeById(id));
    }
}
