package com.ferry.consumer.service.impl;

import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.GossipService;
import com.ferry.server.blog.entity.Gossip;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/12
 */
@Component
public class GossipServiceImpl implements GossipService {
    @GetMapping("/gossip/findAll()")
    public Result findAll(){
        return Result.error();
    }

    @RequestMapping(value = "/gossip/{spitId}", method = RequestMethod.GET)
    public Result findById(String spitId){
        return Result.error();
    }

    @RequestMapping(value = "/gossip", method = RequestMethod.POST)
    public Result save(Gossip gossip){
        return Result.error();
    }

    @RequestMapping(value = "/gossip", method = RequestMethod.PUT)
    public Result update(String gossipId, Gossip gossip){
        return Result.error();
    }

    @RequestMapping(value = "/gossip/{spitId}", method = RequestMethod.DELETE)
    public Result delete(String spitId){
        return Result.error();
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result comment(String parentid, int page, int size){
        return Result.error();
    }

    @RequestMapping(value = "/gossip/pageByUser/{userId}/{page}/{size}", method = RequestMethod.GET)
    public Result pageByUser(String userId, int page, int size) {
        return Result.error();
    }

    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public Result addthumbup(String spitId){
        return Result.error();
    }

    @RequestMapping(value = "/gossip/findAllByPre/{gossipId}", method = RequestMethod.GET)
    public Result findAllByPre(String gossipId) {
        return Result.error();
    }
}
