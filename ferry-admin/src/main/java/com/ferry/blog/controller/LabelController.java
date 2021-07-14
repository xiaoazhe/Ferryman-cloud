package com.ferry.blog.controller;


import com.ferry.blog.service.LabelService;
import com.ferry.common.enums.StateEnums;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.server.blog.entity.BlLabel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/10
 */
@Api(tags = "标签")
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "获取列表")
    @PreAuthorize("hasAuthority('sys:label:view')")
    @PostMapping(value = "/getByPage")
    public Result getByPage(@RequestBody PageRequest pageRequest){
        return Result.ok(labelService.selectAllByUser(pageRequest));
    }

    @ApiOperation(value = "添加标签")
    @PreAuthorize("hasAuthority('sys:label:view')")
    @PostMapping(value = "/saveLabel")
    public Result saveLabel(@RequestBody BlLabel label){
        return Result.ok(labelService.saveLabel(label));
    }

    @ApiOperation(value = "删除标签")
    @PreAuthorize("hasAuthority('sys:label:view')")
    @GetMapping(value = "/deleteLabelById/{id}")
    public Result deleteLabelById(@PathVariable Integer id){
        if (id == null) {
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        return Result.ok(labelService.removeById(id));
    }

    @ApiOperation(value = "查找标签")
    @PreAuthorize("hasAuthority('sys:label:view')")
    @GetMapping(value = "/selectLabelById/{id}")
    public Result selectLabelById(@PathVariable Integer id){
        if (id == null) {
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        return Result.ok(labelService.getById(id));
    }
}
