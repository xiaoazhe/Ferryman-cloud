package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.server.blog.entity.BlFriendLink;
import com.ferry.server.blog.mapper.BlFriendLinkMapper;
import com.ferry.blog.service.FriendService;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: 摆渡人
 * @Date: 2021/5/12
 */
@Service
public class FriendServiceImpl extends ServiceImpl <BlFriendLinkMapper, BlFriendLink> implements FriendService {

    @Autowired
    private BlFriendLinkMapper friendLinkMapper;


    @Override
    public BlFriendLink getById(Integer id) {
        return friendLinkMapper.selectById(id);
    }

    @Override
    public String deleteById(Integer id) {
        friendLinkMapper.deleteById(id);
        return StateEnums.DELETED.getMsg();
    }

    @Override
    public String enableById(Integer id) {
        BlFriendLink friendLink = friendLinkMapper.selectById(id);
        friendLink.setLinkStatus(StateEnums.SUC_CHECK.getCode());
        friendLinkMapper.updateById(friendLink);
        return StateEnums.SUC_CHECK.getMsg();
    }

    @Override
    public String disableById(Integer id) {
        BlFriendLink friendLink = friendLinkMapper.selectById(id);
        friendLink.setLinkStatus(StateEnums.NOT_CHECK.getCode());
        friendLinkMapper.updateById(friendLink);
        return StateEnums.NOT_CHECK.getMsg();
    }

    @Override
    public PageResult getByPage(PageRequest pageRequest) {
        Page <BlFriendLink> page = new Page<>(pageRequest.getPageNum(),
                pageRequest.getPageSize());
        QueryWrapper <BlFriendLink> queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isBlank(pageRequest.getName()),
                BlFriendLink.COL_TITLE, pageRequest.getName());
        Page<BlFriendLink> typePage = friendLinkMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(typePage);
        return pageResult;
    }

    @Override
    public String removeTypes(List <BlFriendLink> friendLinks) {
        for (BlFriendLink friendLink : friendLinks) {
            friendLinkMapper.deleteById(friendLink.getUid());
        }
        return StateEnums.DELETED.getMsg();
    }
}
