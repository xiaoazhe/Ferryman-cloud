package com.ferry.consumer.controller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.CommentService;
import com.ferry.server.blog.entity.BlComment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: 摆渡人
 * @Date: 2021/6/6
 */
@CrossOrigin
@Api(tags = "评论")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "添加评论")
    @PostMapping(value = "save")
    public Result saveComment(@RequestBody BlComment comment) {
        return commentService.add(comment);
    }

    @ApiOperation(value = "获取评论和回复")
    @PostMapping(value = "getCommentAndReply")
    public Result getCommentAndReply(@RequestBody PageRequest pageRequest) {
        return commentService.getCommentAndReply(pageRequest);
    }
}
