package com.ferry.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.IdWorker;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.interceptor.JwtUtil;
import com.ferry.consumer.service.CommentService;
import com.ferry.server.blog.entity.BlComment;
import com.ferry.server.blog.mapper.BlCommentMapper;
import com.ferry.server.blog.mapper.BlUserMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/6
 */
@Service
public class CommentServiceImpl extends ServiceImpl <BlCommentMapper, BlComment> implements CommentService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BlCommentMapper commentMapper;

    @Autowired
    private BlUserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IdWorker idWorker;

    @Override
    public Result add(BlComment comment) {
        String token = (String) request.getAttribute("claims_user");
        Claims claims = jwtUtil.parseJWT(token);
        String userId = claims.getId();
        comment.setCreateBy(userMapper.selectById(userId).getNickname());
        comment.setCreateTime(new Date());
        comment.setUserId(userId);
        comment.setId(idWorker.nextId()+"");
        comment.setStatus(1);
        comment.setType(0);
        int id = commentMapper.insert(comment);
        return new Result().ok(StateEnums.REQUEST_SUCCESS);
    }
}
