package com.ferry.consumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.IdWorker;
import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.interceptor.JwtUtil;
import com.ferry.consumer.service.CommentService;
import com.ferry.server.blog.entity.BlComment;
import com.ferry.server.blog.entity.BlProblem;
import com.ferry.server.blog.entity.BlReply;
import com.ferry.server.blog.mapper.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BlBlogMapper blogMapper;

    @Autowired
    private BlReplyMapper replyMapper;

    @Autowired
    private BlProblemMapper problemMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IdWorker idWorker;

    @Override
    public Result add(BlComment comment) {
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            return null;
        }
        if (comment.getToCommentId() == null || "".equals(comment.getToCommentId())) {
            comment.setFirstCommentId("1");
        }
        comment.setCreateBy(userMapper.selectById(userId).getNickname());
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setUserId(userId);
        comment.setId(idWorker.nextId()+"");
        comment.setStatus(1);
        comment.setType(0);
        comment.setSource("BLOG_INFO");
        commentMapper.insert(comment);
        return new Result().ok(StateEnums.COMMENT_SUC.getMsg());
    }

    @Override
    public Result getCommentAndReply(PageRequest pageRequest) {
        Page <BlComment> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        Page <BlReply> pageReply = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            return null;
        }
        Page <BlComment> comment = commentMapper.selectPage(page, new QueryWrapper <BlComment>().eq(BlComment.COL_USER_ID, userId)
        .ne(BlComment.COL_STATUS, 0));
        for (BlComment com : comment.getRecords()) {
            com.setBlBlog(blogMapper.selectById(com.getBlogId()));
        }
        Page <BlReply> replyPage = replyMapper.selectPage(pageReply, new QueryWrapper <BlReply>().eq(BlReply.COL_USERID, userId));
        for (BlReply reply : replyPage.getRecords()) {
            reply.setProblem(problemMapper.selectById(reply.getProblemid()));
        }
        Map resultMap = new HashMap();
        resultMap.put("commentPage", comment);
        resultMap.put("replyPage", replyPage);
        return new Result().ok(resultMap);
    }


}
