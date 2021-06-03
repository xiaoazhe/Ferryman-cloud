package com.ferry.web.controller;

import com.ferry.core.http.Result;
import com.ferry.web.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/3
 */

@Api(tags = "分类模块")
@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @ApiOperation(value = "查询全部")
    @GetMapping(value="/findAll")
    public Result findAll() {
        return Result.ok(typeService.findAll());
    }
}
