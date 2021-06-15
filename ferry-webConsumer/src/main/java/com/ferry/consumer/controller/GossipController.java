package com.ferry.consumer.controller;

import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.StringUtils;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.interceptor.JwtUtil;
import com.ferry.consumer.service.GossipService;

import com.ferry.server.blog.entity.Gossip;
import com.ferry.server.blog.mapper.BlCommentMapper;
import com.ferry.server.blog.mapper.BlUserMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/12
 */
@CrossOrigin
@RestController
@RequestMapping("/gossip")
public class GossipController {
    @Autowired
    private GossipService gossipService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return gossipService.findAll();
    }

    @RequestMapping(value = "/findAllByPre/{gossipId}", method = RequestMethod.GET)
    public Result findAllByPre(@PathVariable String gossipId){
        if (StringUtils.isBlank(gossipId)){
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        return gossipService.findAllByPre(gossipId);
    }

    @RequestMapping(value = "/{gossipId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String gossipId){
        return gossipService.findById(gossipId);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody Gossip gossip){
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            String userId = claims.getId();
            gossip.setUserid(userId);
        } catch (Exception e) {
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        if (gossip.getParentid() == null) {
            gossip.setParentid("0");
        }
        return gossipService.save(gossip);
    }

    @RequestMapping(value = "/{gossipId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String gossipId, @RequestBody Gossip gossip){
        gossip.set_id(gossipId);
        return gossipService.update(gossipId, gossip);
    }

    @RequestMapping(value = "/{gossipId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String gossipId){
        return gossipService.delete(gossipId);
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        return gossipService.comment(parentid, page, size);
    }

    @RequestMapping(value = "/pageByUser/{page}/{size}", method = RequestMethod.GET)
    public Result pageByUser(@PathVariable int page, @PathVariable int size){
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        return gossipService.pageByUser(userId, page, size);
    }

    @RequestMapping(value = "/thumbup/{gossipId}", method = RequestMethod.PUT)
    public Result addthumbup(@PathVariable String gossipId){
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            return Result.error(StateEnums.REQUEST_ERROR.getMsg());
        }
        //先判断该用户是否已经点赞了。
        if(redisTemplate.opsForValue().get("gossip_"+userId+"_"+gossipId)!=null){
            return Result.error(StateEnums.ADDLIKE_ERR.getMsg());
        }
        if (gossipService.addthumbup(gossipId).getCode() == 200) {
            redisTemplate.opsForValue().set("gossip_"+userId+"_"+gossipId, 1);
            return Result.ok(StateEnums.ADDLIKE_SUC.getMsg());
        }
        return Result.ok(StateEnums.ADDLIKE_ERR.getMsg());
    }
}
