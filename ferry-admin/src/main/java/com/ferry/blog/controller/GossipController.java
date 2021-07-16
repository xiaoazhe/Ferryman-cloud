package com.ferry.blog.controller;

import com.ferry.blog.service.GossipService;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.file.object.PageResult;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.server.blog.entity.Gossip;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/7/6
 */
@Api(tags = "交流")
@RestController
@RequestMapping("/gossip")
public class GossipController {

    @Autowired
    private GossipService gossipService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return Result.ok( gossipService.findAll());
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @RequestMapping(value = "/findAllByPre/{gossipId}", method = RequestMethod.GET)
    public Result findAllByPre(@PathVariable String gossipId){
        return Result.ok(gossipService.findAllByPre(gossipId));
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @RequestMapping(value = "/{gossipId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String gossipId){
        return Result.ok( gossipService.findById(gossipId));
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Gossip gossip){
        if (StringUtils.isBlank(gossip.getParentid())) {
            gossip.setParentid("0");
        }
        gossipService.save(gossip);
        return Result.ok(StateEnums.SAVEBLOG_SUC.getMsg());
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @RequestMapping(value = "/{gossipId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String gossipId, @RequestBody Gossip gossip){
        gossip.set_id(gossipId);
        gossipService.update(gossip);
        return Result.ok(StateEnums.SAVEBLOG_SUC.getMsg());
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @RequestMapping(value = "/{gossipId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String gossipId){
        gossipService.deleteById(gossipId);
        return Result.ok(StateEnums.DELETED.getMsg());
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @PostMapping(value = "/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest){
        Page <Gossip> pageData = gossipService.findPage(pageRequest.getPageNum(),
                pageRequest.getPageSize(), pageRequest.getName());
        return Result.ok(new PageResult <Gossip>(pageData.getTotalElements(), pageData.getContent()));
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Page <Gossip> pageData = gossipService.pageQuery(parentid, page, size);
        return Result.ok(new PageResult <Gossip>(pageData.getTotalElements(), pageData.getContent()));
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @RequestMapping(value = "/pageByUser/{userId}/{page}/{size}", method = RequestMethod.GET)
    public Result pageByUser(@PathVariable String userId, @PathVariable int page, @PathVariable int size){
        Page <Gossip> pageData = gossipService.pageByUser(userId, page, size);
        return Result.ok(new PageResult <Gossip>(pageData.getTotalElements(), pageData.getContent()));
    }

    @PreAuthorize("hasAuthority('sys:gossip:view')")
    @RequestMapping(value = "/thumbup/{gossipId}", method = RequestMethod.PUT)
    public Result addthumbup(@PathVariable String gossipId){
        gossipService.addthumbup(gossipId);
        return Result.ok(StateEnums.ADDLIKE_SUC.getMsg());
    }
}
