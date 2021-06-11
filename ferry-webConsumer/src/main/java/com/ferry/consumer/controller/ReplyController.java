package com.ferry.consumer.controller;


import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.ReplyService;
import com.ferry.consumer.utils.UserUtils;
import com.ferry.server.blog.entity.BlReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
@CrossOrigin
@Api(tags = "回复")
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @ApiOperation(value = "获取最新回复")
    @GetMapping(value = "/newlist")
    public Result newlist(){
        return Result.ok(replyService.newlist());
    }

    @ApiOperation(value = "获取最新回复")
    @PostMapping(value = "/getByProId")
    public Result getByProId(@RequestParam String proId, @RequestBody PageRequest pageRequest){
        return Result.ok(replyService.getByProId(proId, pageRequest));
    }

    @ApiOperation(value = "添加回复")
    @PostMapping(value = "/save")
    public Result save(@RequestBody BlReply reply){
        return Result.ok(replyService.add(reply));
    }

    @ApiOperation(value = "获取最新回复")
    @GetMapping(value = "/delectComment/{id}")
    public Result delectComment(@PathVariable String id){
        return Result.ok(replyService.removeById(id));
    }
}
