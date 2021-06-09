package com.ferry.consumer.controller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.ProblemService;
import com.ferry.server.blog.entity.BlProblem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: 摆渡人
 * @Date: 2021/6/6
 */
@CrossOrigin
@Api(tags = "问题")
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @ApiOperation(value = "获取最新列表")
    @PostMapping(value = "/newlist")
    public Result newlist(@RequestParam Integer labelId, @RequestBody PageRequest pageRequest){
        return Result.ok(problemService.newlist(labelId, pageRequest));
    }

    @ApiOperation(value = "热门列表")
    @PostMapping(value = "/hotlist")
    public Result hotlist(@RequestParam Integer labelId, @RequestBody PageRequest pageRequest){
        return Result.ok(problemService.hotlist(labelId, pageRequest));
    }

    @ApiOperation(value = "没有回答列表")
    @PostMapping(value = "/waitlist")
    public Result waitlist(@RequestParam Integer labelId, @RequestBody PageRequest pageRequest){
        return Result.ok(problemService.waitlist(labelId, pageRequest));
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value="/getById")
    public Result findById(@RequestParam String id){
        return Result.ok(problemService.getProById(id));
    }

    @ApiOperation(value = "添加")
    @PostMapping(value="/savePro")
    public Result savePro(@RequestBody BlProblem problem){
        return Result.ok(problemService.savePro(problem));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/deleteById")
    public Result delete(@PathVariable String id ){
        return Result.ok(problemService.deleteById(id));
    }
}
