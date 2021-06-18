package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.blog.service.ProblemService;
import com.ferry.common.enums.StateEnums;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.*;
import com.ferry.server.blog.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
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
        Page <BlProblem> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<BlProblem> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc(BlProblem.COL_CREATETIME);
        Page<BlProblem> problemPage = problemMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(problemPage);
        return pageResult;
    }

    @Override
    public String deleteById(String id) {
        problemMapper.deleteById(id);
        redisTemplate.delete("pro_" + id);
        return StateEnums.DELETED.getMsg();
    }
}
