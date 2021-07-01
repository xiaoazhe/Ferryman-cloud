package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.blog.service.UserService;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlUser;
import com.ferry.server.blog.mapper.BlUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 摆渡人
 * @Date: 2021/7/1
 */
@Service
public class UserServiceImpl extends ServiceImpl <BlUserMapper, BlUser> implements UserService {

    @Autowired
    private BlUserMapper userMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Page <BlUser> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        String label = pageRequest.getName();
        QueryWrapper <BlUser> queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isBlank(label),BlUser.COL_NICKNAME, label);
        Page<BlUser> typePage = userMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(typePage);
        return pageResult;
    }
}
