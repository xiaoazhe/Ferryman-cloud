package com.ferry.consumer.controller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/17
 */
@CrossOrigin
@Api(tags = "资料链接")
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @ApiOperation(value = "分页列表")
    @PostMapping(value = "/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest){
        return Result.ok(materialService.findPage(pageRequest));
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "findById/{id}")
    public Result findById(@PathVariable String id) {
        return Result.ok(materialService.findById(id));
    }
}
