package com.ferry.admin.controller;

import com.ferry.admin.service.SysNotifyRecordService;
import com.ferry.admin.service.SysNotifyService;
import com.ferry.admin.vo.NotifyVo;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 菜单管理
 *
 * @Author: 摆渡人
 * @Date: 2021/9/13
 */
@Api(tags = "系统通知")
@RestController
@RequestMapping(value = "notify")
public class NotifyController {

    @Autowired
    private SysNotifyService notifyService;

    @Autowired
    private SysNotifyRecordService notifyRecordService;

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:notify:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(notifyService.findPage(pageRequest));
    }

    @ApiOperation(value = "添加通知")
    @PreAuthorize("hasAuthority('sys:notify:add') AND hasAuthority('sys:notify:edit')")
    @PostMapping(value = "/save")
    public Result save(@RequestBody NotifyVo notify) {
        return Result.ok(notifyService.saveOrUpdateNotify(notify));
    }

    @ApiOperation(value = "删除菜单")
    @PreAuthorize("hasAuthority('sys:notify:delete')")
    @GetMapping(value = "/delete/{id}")
    public Result delete(@RequestParam Integer id) {
        return Result.ok(notifyService.removeById(id));
    }

    @ApiOperation(value = "已读")
    @PreAuthorize("hasAuthority('sys:notify:edit')")
    @GetMapping(value = "/readNotify/{id}")
    public Result readNotify(@RequestParam Integer id) {
        return Result.ok(notifyService.readNotify(id));
    }
}
