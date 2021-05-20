package com.ferry.admin.controller;

import com.ferry.server.admin.entity.SysLoginLog;
import com.ferry.admin.service.SysLoginLogService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 登录日志模块
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@Api(tags = "登录日志")
@RequestMapping("loginlog")
@RestController
public class LoginLogController {


    @Autowired
    private SysLoginLogService sysLoginLogService;

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:loginlog:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(sysLoginLogService.findPage(pageRequest));
    }

    @ApiOperation(value = "删除日志")
    @PreAuthorize("hasAuthority('sys:loginlog:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <SysLoginLog> records) {
        return Result.ok(sysLoginLogService.delete(records));
    }
}
