package com.ferry.consumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.IdWorker;
import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.interceptor.JwtUtil;
import com.ferry.consumer.service.ProblemService;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.*;
import com.ferry.server.blog.mapper.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
@Service
public class ProblemServiceImpl extends ServiceImpl <BlProblemMapper, BlProblem> implements ProblemService {

    @Autowired
    private BlProblemMapper problemMapper;

    @Autowired
    private BlProLabelMapper proLabelMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BlUserMapper userMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BlCollectMapper collectMapper;

    @Autowired
    private BlBlogMapper blogMapper;

    @Override
    public PageResult getIndividualPro(PageRequest pageRequest) {
        Page <BlProblem> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            throw new RuntimeException(StateEnums.REQUEST_ERROR.getMsg());
        }
        QueryWrapper<BlProblem> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(BlProblem.COL_USERID, userId);
        Page<BlProblem> problemPage = problemMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(problemPage);
        return pageResult;
    }

    @Override
    public PageResult newlist(Integer labelId, PageRequest pageRequest) {
        return getPage(labelId, pageRequest, BlProblem.COL_REPLYTIME);
    }

    @Override
    public PageResult hotlist(Integer labelId, PageRequest pageRequest) {
        return getPage(labelId, pageRequest, BlProblem.COL_REPLY);
    }

    @Override
    public PageResult waitlist(Integer labelId, PageRequest pageRequest) {
        return getPage(labelId, pageRequest, BlProblem.COL_CREATETIME);
    }

    @Override
    public BlProblem getProById(String id) {
        BlProblem problem = (BlProblem)redisTemplate.opsForValue().get("pro_" + id);
        if (problem == null) {
            problem = problemMapper.selectById(id);
            redisTemplate.opsForValue().set("pro_"+id, problem, 20, TimeUnit.SECONDS);
        }
        return problem;
    }

    @Override
    public String savePro(BlProblem problem) {
        String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
        Claims claims = jwtUtil.parseJWT(token);
        String userId = claims.getId();
        BlUser user = userMapper.selectById(userId);
        problem.setCreateTime(new Date());
        problem.setCreateBy(user.getNickname());
        problem.setUserid(userId);
        problem.setNickname(user.getNickname());
        String proId = idWorker.nextId() + "";
        problem.setId(proId);
        problemMapper.insert(problem);
        for (BlLabel label : problem.getLabelList()) {
            BlProLabel proLabel = new BlProLabel();
            proLabel.setProblemid(proId);
            proLabel.setLabelid(String.valueOf(label.getId()));
            proLabelMapper.insert(proLabel);
        }
        return StateEnums.SAVEBLOG_SUC.getMsg();
    }

    @Override
    public String deleteById(String id) {
        problemMapper.deleteById(id);
        redisTemplate.delete("pro_" + id);
        return StateEnums.DELETED.getMsg();
    }

    @Override
    public String setGood(String id) {
        String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
        Claims claims = jwtUtil.parseJWT(token);
        String userId = claims.getId();
        Boolean userLike = (Boolean) redisTemplate.opsForValue().get("user_pro_like_" + userId + "_" + id);
        if (userLike == null || !userLike) {
            BlProblem blProblem = problemMapper.selectById(id);
            blProblem.setThumbup(blProblem.getThumbup() + 1);
            redisTemplate.opsForValue().set("user_pro_like_" + userId + "_" + id, true, 1, TimeUnit.DAYS);
            problemMapper.updateById(blProblem);
        } else {
            return StateEnums.ADDLIKE_ERR.getMsg();
        }
        return StateEnums.ADDLIKE_SUC.getMsg();
    }

    @Override
    public String setCollect(String id, Integer statusId) {
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            throw new RuntimeException(StateEnums.REQUEST_ERROR.getMsg());
        }
        BlCollect collect = new BlCollect();
        if (statusId == 1) {
            collect.setBlogId(id);
        } else {
            collect.setProId(id);
        }
        collect.setUserId(userId);
        collect.setCreateBy(userId);
        collect.setCreateTime(new Date());
        collectMapper.insert(collect);
        return StateEnums.COLLECT_SUC.getMsg();
    }

    @Override
    public PageResult getCollect(String id, Integer statusId, PageRequest pageRequest) {
        String userId = null;
        try {
            String token = request.getHeader(FieldStatusEnum.HEARD).substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            throw new RuntimeException(StateEnums.REQUEST_ERROR.getMsg());
        }
        QueryWrapper<BlCollect> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(BlCollect.COL_USER_ID, userId);
        queryWrapper.eq(BlCollect.COL_STATUS, statusId);
        List<BlCollect> collectList = collectMapper.selectList(queryWrapper);
        List <String> ids = new ArrayList <>();
        if (statusId == 1) {
            Page <BlBlog> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
            ids = collectList.stream().map(BlCollect::getBlogId).collect(Collectors.toList());
            QueryWrapper blogQuery = new QueryWrapper<>();
            blogQuery.in(BlBlog.COL_ID, ids);
            Page<BlBlog> blBlogPage = blogMapper.selectPage(page, blogQuery);
            PageResult pageResult = new PageResult(blBlogPage);
            return pageResult;
        } else if (statusId == 2){
            Page <BlProblem> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
            ids = collectList.stream().map(BlCollect::getProId).collect(Collectors.toList());
            QueryWrapper blogQuery = new QueryWrapper<>();
            blogQuery.in(BlProblem.COL_ID, ids);
            Page<BlProblem> problemPage = problemMapper.selectPage(page, blogQuery);
            PageResult pageResult = new PageResult(problemPage);
            return pageResult;
        } else {
            Page <BlBlog> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
            ids = collectList.stream().filter(collect -> collect.getStatus() == 3)
                    .map(BlCollect::getBlogId).collect(Collectors.toList());
            QueryWrapper blogQuery = new QueryWrapper<>();
            blogQuery.in(BlBlog.COL_ID, ids);
            Page<BlBlog> blBlogPage = blogMapper.selectPage(page, blogQuery);
            PageResult pageResult = new PageResult(blBlogPage);
            return pageResult;
        }
    }

    @Override
    public String deleteCollect(Integer id) {
        collectMapper.deleteById(id);
        return StateEnums.DELETED.getMsg();
    }

    public PageResult getPage (Integer labelId, PageRequest pageRequest, String parameter) {
        Page <BlProblem> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        List <BlProLabel> proLabelList = new ArrayList <>();
        if (labelId == 0) {
            proLabelList = proLabelMapper.selectAll();
        } else {
            Map map = new HashMap();
            map.put(BlProLabel.COL_LABELID, labelId);
            proLabelList = proLabelMapper.selectByMap(map);
        }
        List<String> proIds = proLabelList.stream().
                map(BlProLabel::getProblemid).collect(Collectors.toList());
        QueryWrapper<BlProblem> queryWrapper = new QueryWrapper();
        if (BlProblem.COL_CREATETIME.equals(parameter)) {
            queryWrapper.in(BlProblem.COL_REPLY, 0);
        }
        queryWrapper.in(BlProblem.COL_ID, proIds);
        queryWrapper.orderByDesc(parameter);
        Page<BlProblem> problemPage = problemMapper.selectPage(page, queryWrapper);
        for (BlProblem pro: problemPage.getRecords()) {
            Map proLabMap = new HashMap();
            proLabMap.put(BlProLabel.COL_PROBLEMID, pro.getId());
            List <BlLabel> proLabels = proLabelMapper.selectByMap(proLabMap);
            pro.setLabelList(proLabels);
        }
        PageResult pageResult = new PageResult(problemPage);
        return pageResult;
    }
}
