package com.ferry.web.controller;

import com.ferry.common.enums.StateEnums;
import com.ferry.core.http.Result;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.web.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/2
 */

@Api(tags = "前台系统")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserService userService;

//    @Autowired
//    private JwtUtil jwtUtil;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody SysUser user){
        user = userService.login(user.getMobile(), user.getPassword());
        if(user==null){
            return new Result().error();
        }
//        String token = jwtUtil.createJWT(String.valueOf(user.getId()), user.getMobile(), "user");
        Map <String, Object> map = new HashMap <>();
//        map.put("token", token);
        map.put("roles", "user");
        return new Result().ok(StateEnums.REQUEST_SUCCESS);
    }

}
