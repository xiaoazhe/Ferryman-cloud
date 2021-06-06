package com.ferry.consumer.controller;

import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.CommentService;
import com.ferry.server.blog.entity.BlComment;
import io.swagger.annotations.Api;
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

    @PostMapping(value = "save")
    public Result saveComment(@RequestBody BlComment comment) {
        return commentService.add(comment);
    }
}
