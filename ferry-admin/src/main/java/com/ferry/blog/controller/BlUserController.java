package com.ferry.blog.controller;

import com.ferry.blog.amqp.DeadLetterSender;
import com.ferry.blog.service.UserService;
import com.ferry.common.enums.StateEnums;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/7/1
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/blog/user")
public class BlUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeadLetterSender deadLetterSender;

    @ApiOperation(value = "获取列表")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping(value = "/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest){
        return Result.ok(userService.findPage(pageRequest));
    }

    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @GetMapping(value = "/delete/{id}")
    public Result delete(@PathVariable String id){
        if (id == null) {
            return Result.error();
        }
        try {
            deadLetterSender.send(id,1000 * 60 * 60 * 24 *3); // 三天后消费
        } catch (Exception e) {
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        return Result.ok();
    }
}
