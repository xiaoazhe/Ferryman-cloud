package com.ferry.blog.controller;


import com.ferry.common.enums.StateEnums;
import com.ferry.server.blog.entity.BlMusic;
import com.ferry.blog.service.MusicService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

/**
 * @Author: 码仔
 * @Date: 2020/2/9 20:45
 * @Version 1.0
 */
@Api(tags = "音乐模块")
@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @PostMapping(value="/mail1")
    public void testSendSimple() {
        try {
            System.out.println("tt");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo("1540035958@qq.com");
            message.setSubject("标题：测试标题");
            message.setText("测试内容部份");
            mailSender.send(message);
        } catch (Exception e) {
            return;
        }
    }
    @PostMapping(value="/mail")
    public void futestSendSimple() {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            System.out.println("tt");
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
            message.setFrom(from);
            message.setTo("1540035958@qq.com");
            message.setSubject("标题：测试标题");
            message.setText("<h1>富文本</h1>", true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @PostMapping(value="/getByPage")
    public Result findPage(@RequestBody PageRequest page) {
        return Result.ok(musicService.getByPage(page));
    }

    /**
     * 保存
     *
     * @param music
     * @return
     */
    @ApiOperation(value = "保存音乐")
    @PreAuthorize("hasAuthority('sys:music:add')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody BlMusic music) {
        if (music.getId() != null) {
            music.setUpdateTime(new Date());
            return Result.ok(musicService.updateById(music));
        }
        return Result.ok(musicService.save(music));
    }

    /**
     * 更新
     *
     * @param music
     * @return
     */
    @ApiOperation(value = "更新音乐")
    @PreAuthorize("hasAuthority('sys:music:edit')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlMusic music) {
        musicService.updateById(music);
        return Result.ok(StateEnums.REQUEST_SUCCESS.getMsg());
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询音乐")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result get(@PathVariable String id) {
        return Result.ok(musicService.getById(id));
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除音乐")
    @PreAuthorize("hasAuthority('sys:music:delete')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id) {
        musicService.deleteById(id);
        return Result.ok(StateEnums.DELETED.getMsg());
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "状态启用")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable Integer id) {
        musicService.enableById(id);
        return Result.ok(StateEnums.ENABLED.getMsg());
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "状态弃用")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable Integer id) {
        musicService.disableById(id);
        return Result.ok(StateEnums.NOT_ENABLE.getMsg());
    }

}
