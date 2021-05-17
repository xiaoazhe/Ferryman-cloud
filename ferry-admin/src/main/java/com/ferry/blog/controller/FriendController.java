package com.ferry.blog.controller;

import com.ferry.blog.entity.BlFriendLink;
import com.ferry.blog.service.FriendService;
import com.ferry.common.utils.IdWorker;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/12
 */
@Api(tags = "友情链接")
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @PostMapping(value="/getByPage")
    public Result findPage(@RequestBody PageRequest page) {
        return Result.ok(friendService.getByPage(page));
    }

    /**
     * 保存
     *
     * @param friendLink
     * @return
     */
    @ApiOperation(value = "保存链接")
    @PreAuthorize("hasAuthority('sys:friend:add')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody BlFriendLink friendLink) {
        if (friendLink.getUid() != null) {
            friendLink.setUpdateTime(new Date());
            return Result.ok(friendService.updateById(friendLink));
        }
        friendLink.setUid(idWorker.nextId()+"");
        friendLink.setCreateTime(new Date());
        return Result.ok(friendService.save(friendLink));
    }

    /**
     * 更新
     *
     * @param friendLink
     * @return
     */
    @ApiOperation(value = "更新链接")
    @PreAuthorize("hasAuthority('sys:friend:edit')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlFriendLink friendLink) {
        return Result.ok(friendService.updateById(friendLink));
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询")
    @PreAuthorize("hasAuthority('sys:friend:view')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result get(@PathVariable String id) {
        BlFriendLink friendLink = friendService.getById(id);
        return Result.ok(friendLink);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除链接")
    @PreAuthorize("hasAuthority('sys:friend:delete')")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable Integer id) {
        return Result.ok(friendService.deleteById(id));
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "状态启用")
    @PreAuthorize("hasAuthority('sys:friend:view')")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable Integer id) {
        return Result.ok(friendService.enableById(id));
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "状态弃用")
    @PreAuthorize("hasAuthority('sys:friend:view')")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable Integer id) {
        return Result.ok(friendService.disableById(id));
    }

    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('sys:friend:delete')")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List <BlFriendLink> friendLinks) {
        return Result.ok(friendService.removeTypes(friendLinks));
    }
}
