package com.ferry.web.controller;

import com.ferry.common.enums.CommonStatusEnum;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.web.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/3
 */

@Api(tags = "分类模块")
@RestController
@CrossOrigin
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @ApiOperation(value = "查询全部")
    @GetMapping(value = "/findAll")
    public Result findAll() {
        return Result.ok(typeService.findAll());
    }

    @ApiOperation(value = "根据类型查询博客")
    @PostMapping(value = "/findBlogByTypeId")
    public Result findBlogByTypeId(@RequestBody PageRequest pageRequest) {
        String typeId = String.valueOf(pageRequest.getEnabled());
        if (typeId == null || "-1" .equals(typeId)) {
            throw new RuntimeException(CommonStatusEnum.NO_FIND);
        }
        return Result.ok(typeService.findBlogPage(pageRequest, typeId));
    }

}
