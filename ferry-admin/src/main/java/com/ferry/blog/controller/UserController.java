package com.ferry.blog.controller;

import com.ferry.blog.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        return Result.ok(userService.removeById(id));
    }
}
