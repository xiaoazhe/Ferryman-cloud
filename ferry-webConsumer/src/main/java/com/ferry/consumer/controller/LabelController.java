package com.ferry.consumer.controller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/10
 */
@CrossOrigin
@Api(tags = "标签")
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "获取列表")
    @PostMapping(value = "/newlist")
    public Result newlist(@RequestBody PageRequest pageRequest){
        return Result.ok(labelService.selectAllByUser(pageRequest));
    }

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/toplist")
    public Result toplist(){
        return Result.ok(labelService.toplist());
    }

}
