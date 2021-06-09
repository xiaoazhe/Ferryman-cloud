package com.ferry.consumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.service.ProblemService;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlLabel;
import com.ferry.server.blog.entity.BlProLabel;
import com.ferry.server.blog.entity.BlProblem;
import com.ferry.server.blog.mapper.BlProLabelMapper;
import com.ferry.server.blog.mapper.BlProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
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

    @Override
    public PageResult newlist(Integer labelId, PageRequest pageRequest) {
        return getPage(labelId,pageRequest, BlProblem.COL_REPLYTIME);
    }

    @Override
    public PageResult hotlist(Integer labelId, PageRequest pageRequest) {
        return getPage(labelId,pageRequest, BlProblem.COL_REPLY);
    }

    @Override
    public PageResult waitlist(Integer labelId, PageRequest pageRequest) {
        return getPage(labelId,pageRequest, BlProblem.COL_CREATETIME);
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

    public PageResult getPage (Integer labelId, PageRequest pageRequest, String parameter) {
        Page <BlProblem> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        Map map = new HashMap();
        map.put(BlProLabel.COL_LABELID, labelId);
        List <BlProLabel> proLabelList = proLabelMapper.selectByMap(map);
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
