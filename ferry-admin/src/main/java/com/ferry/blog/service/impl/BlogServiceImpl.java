package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.server.blog.entity.BlBlog;
import com.ferry.server.blog.entity.BlType;
import com.ferry.server.blog.mapper.BlBlogMapper;
import com.ferry.server.blog.mapper.BlTypeMapper;
import com.ferry.blog.service.BlogService;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.IdWorker;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
@Service
public class BlogServiceImpl extends ServiceImpl <BlBlogMapper, BlBlog> implements BlogService {

    @Autowired
    private BlBlogMapper blogMapper;

    @Autowired
    private BlTypeMapper typeMapper;

    @Autowired
    private IdWorker idWorker;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Page <BlBlog> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        String label = pageRequest.getName();
        QueryWrapper<BlBlog> queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isBlank(label),BlBlog.COL_TITLE, label);
        Page<BlBlog> typePage = blogMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(typePage);
        return pageResult;
    }

    @Override
    public boolean removeTypes(List <BlBlog> blBlogs) {
        for (BlBlog blBlog : blBlogs) {
            blogMapper.deleteById(blBlog.getId());
        }
        return true;
    }

    @Override
    public boolean saveBlog(BlBlog blBlog) {
        BlType type = null;
        if (blBlog.getTypeId() == null && blBlog.getTypeName() == null) {
            blBlog.setTypeId("1");
        }
        if (isNumeric(blBlog.getTypeName())) {
            type = typeMapper.selectById(blBlog.getTypeName());
        } else {
            type = typeMapper.selectById(blBlog.getTypeId());
        }
        if(blBlog.getId() != null) {
            BlBlog oldBlog = blogMapper.selectById(blBlog.getId());
            if (oldBlog != null) {
                oldBlog.setUpdateTime(new Date());
                oldBlog.setSort(blBlog.getSort());
                oldBlog.setTitle(blBlog.getTitle());
                oldBlog.setSummary(blBlog.getSummary());
                oldBlog.setContent(blBlog.getContent());
                oldBlog.setTypeName(type.getName());
                oldBlog.setFileUid(blBlog.getFileUid());
                oldBlog.setTypeId(String.valueOf(type.getId()));
                blogMapper.updateById(oldBlog);
            }
        } else {
            blBlog.setTypeName(type.getName());
            blBlog.setTypeId(String.valueOf(type.getId()));
            blBlog.setId(idWorker.nextId() + "");
            blBlog.setCreateTime(new Date());
            blogMapper.insert(blBlog);
        }
        return true;
    }

    @Override
    public String deleteById(String id) {
        blogMapper.deleteById(id);
        return StateEnums.DELETED.getMsg();
    }

    @Override
    public BlBlog selectById(String id) {
        return blogMapper.selectById(id);
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
