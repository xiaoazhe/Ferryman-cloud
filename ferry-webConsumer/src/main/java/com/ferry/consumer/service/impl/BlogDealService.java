package com.ferry.consumer.service.impl;

import com.ferry.consumer.http.Result;
import com.ferry.consumer.interceptor.JwtUtil;
import com.ferry.consumer.service.BlogService;
import com.ferry.server.blog.entity.BlBlog;
import com.ferry.server.blog.mapper.BlUserMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/8
 */
@Service
public class BlogDealService {
    @Autowired
    private BlogService blogService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BlUserMapper userMapper;

    public Result saveBlog(BlBlog blBlog) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        Claims claims = jwtUtil.parseJWT(token);
        String userId = claims.getId();
        String name = userMapper.selectById(userId).getNickname();
        blBlog.setCreateBy(name);
        blBlog.setAuthor(name);
        return blogService.saveBlog(blBlog);
    }
}
