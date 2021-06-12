package com.ferry.consumer.service;

import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.impl.GossipServiceImpl;
import com.ferry.server.blog.entity.Gossip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
@FeignClient(name = "Ferry-gossip", fallback = GossipServiceImpl.class)
public interface GossipService {

    @GetMapping("/gossip/findAll()")
    public Result findAll();

    @RequestMapping(value = "/gossip/{gossipId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String gossipId);

    @RequestMapping(value = "/gossip", method = RequestMethod.POST)
    public Result save(Gossip gossip);

    @RequestMapping(value = "/gossip/{gossipId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String gossipId, Gossip gossip);

    @RequestMapping(value = "/gossip/{gossipId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String gossipId);

    @RequestMapping(value = "/gossip/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size);

    @RequestMapping(value = "/gossip/thumbup/{gossipId}", method = RequestMethod.PUT)
    public Result addthumbup(@PathVariable String gossipId);

    @RequestMapping(value = "/gossip/findAllByPre/{gossipId}", method = RequestMethod.GET)
    Result findAllByPre(@PathVariable String gossipId);
}
