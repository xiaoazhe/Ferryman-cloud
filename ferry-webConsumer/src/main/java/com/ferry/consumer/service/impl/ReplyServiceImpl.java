package com.ferry.consumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.consumer.interceptor.JwtUtil;
import com.ferry.consumer.service.ReplyService;
import com.ferry.consumer.utils.UserUtils;
import com.ferry.server.blog.entity.*;
import com.ferry.server.blog.mapper.BlLabelMapper;
import com.ferry.server.blog.mapper.BlReplyMapper;
import com.ferry.server.blog.mapper.BlUserLabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
@Service
public class ReplyServiceImpl extends ServiceImpl <BlReplyMapper, BlReply> implements ReplyService {


    @Autowired
    private BlReplyMapper replyMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public List <BlReply> newlist() {
        String userId = UserUtils.getUserId(request, jwtUtil);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(BlReply.COL_USERID, userId);
        queryWrapper.orderByDesc(BlReply.COL_CREATETIME);
        List <BlReply> replies = replyMapper.selectList(queryWrapper);
        return replies.stream().limit(5).collect(Collectors.toList());
    }
}
