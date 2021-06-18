package com.ferry.blog.controller;


import com.ferry.blog.service.LabelService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
}
