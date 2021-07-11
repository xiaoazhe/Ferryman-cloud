package com.ferry.blog.controller;

import com.ferry.blog.service.ProblemService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: 摆渡人
 * @Date: 2021/6/6
 */
@Api(tags = "问题")
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @ApiOperation(value = "获取列表")
    @PreAuthorize("hasAuthority('sys:label:view')")
    @PostMapping(value = "/newlist")
    public Result newlist(@RequestBody PageRequest pageRequest){
        return Result.ok(problemService.newlist(pageRequest));
    }

    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('sys:label:delete')")
    @GetMapping(value="/deleteById/{id}")
    public Result delete(@PathVariable String id){
        return Result.ok(problemService.deleteById(id));
    }
}
