package com.ferry.blog.controller;

import com.ferry.blog.service.CommentService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/11
 */
@Api(tags = "评论模块")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:comment:view')")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.ok(commentService.findPage(pageRequest));
    }

    /**
     * 根据id查询子评论
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询子评论")
    @PreAuthorize("hasAuthority('sys:comment:delete')")
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return Result.ok(commentService.findById(id));
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除评论")
    @PreAuthorize("hasAuthority('sys:comment:delete')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        return Result.ok(commentService.deleteById(id));
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除父子评论")
    @PreAuthorize("hasAuthority('sys:comment:delete')")
    @RequestMapping(value = "/deleteAll/{id}", method = RequestMethod.DELETE)
    public Result deleteAll(@PathVariable String id) {
        return Result.ok(commentService.deleteAll(id));
    }
}
