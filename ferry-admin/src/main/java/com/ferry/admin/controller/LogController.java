package com.ferry.admin.controller;

import com.ferry.admin.entity.SysLog;
import com.ferry.admin.service.SysLogService;
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
 * 日志模块
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@Api(value = "日志管理")
@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:log:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(sysLogService.findPage(pageRequest));
    }

    @ApiOperation(value = "删除日志")
    @PreAuthorize("hasAuthority('sys:log:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <SysLog> records) {
        return Result.ok(sysLogService.delete(records));
    }

}
