package com.ferry.consumer.controller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.FriendLinkService;
import com.ferry.server.blog.entity.BlFriendLink;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/17
 */
@CrossOrigin
@Api(tags = "友情鏈接")
@RestController
@RequestMapping("/link")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @ApiOperation(value = "申请")
    @PostMapping(value = "save")
    public Result save(@RequestBody BlFriendLink friendLink) {
        return Result.ok(friendLinkService.add(friendLink));
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "findById/{id}")
    public Result findById(@PathVariable String id) {
        return Result.ok(friendLinkService.findById(id));
    }

    @ApiOperation(value = "分页列表")
    @PostMapping(value = "/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest){
        return Result.ok(friendLinkService.findPage(pageRequest));
    }

    @ApiOperation(value = "top5")
    @GetMapping(value = "/friendTop")
    public Result friendTop(){
        return Result.ok(friendLinkService.friendTop());
    }
}
