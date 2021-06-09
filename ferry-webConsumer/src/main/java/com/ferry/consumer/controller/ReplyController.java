package com.ferry.consumer.controller;


import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.ReplyService;
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

}
