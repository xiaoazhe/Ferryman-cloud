package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.server.blog.entity.BlComment;
import com.ferry.server.blog.entity.BlType;
import com.ferry.server.blog.mapper.BlCommentMapper;
import com.ferry.server.blog.mapper.BlTypeMapper;
import com.ferry.blog.service.CommentService;
import com.ferry.common.enums.EStatus;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/11
 */
@Service
public class CommentServiceImpl extends ServiceImpl <BlCommentMapper, BlComment> implements CommentService {

    @Autowired
    private BlCommentMapper commentMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Page <BlComment> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        String label = pageRequest.getParamValue("name");
        QueryWrapper<BlComment> queryWrapper = new QueryWrapper<BlComment>();
        queryWrapper.like(!StringUtils.isBlank(label),"bl_comment.content",label);
        queryWrapper.ne("bl_comment.status",0);
        Page<BlComment> typePageNew = commentMapper.selectPageNew(page, queryWrapper);
        //Page<BlComment> typePage = commentMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(typePageNew);
        return pageResult;
    }

    @Override
    public String deleteById(String id) {
        BlComment comment = commentMapper.selectById(id);
        comment.setStatus(EStatus.DISABLED);
        comment.setUpdateTime(new Date());
        commentMapper.updateById(comment);
        return StateEnums.DELETED.getMsg();
    }

    @Override
    public String deleteAll(String id) {
        QueryWrapper<BlComment> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq("to_comment_id", id).or().eq("id", id);
        queryWrapper.ne("status",0);
        List <BlComment> commentList = commentMapper.selectList(queryWrapper);
        for (BlComment comment: commentList) {
            comment.setStatus(EStatus.DISABLED);
            comment.setUpdateTime(new Date());
        }
        commentMapper.updateBatch(commentList);
        return StateEnums.DELETED.getMsg();
    }

    @Override
    public BlComment findById(String id) {
        BlComment comment = commentMapper.selectById(id);
        QueryWrapper<BlComment> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq("to_comment_id", id);
        List<BlComment> childComment= commentMapper.selectCommentList(queryWrapper);
        comment.setBlogName(childComment.get(0).getBlogName());
//        List<BlComment> childComment= commentMapper.selectList(queryWrapper);
        comment.setCommentList(childComment);
        return comment;
    }
}
