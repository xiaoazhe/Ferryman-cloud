package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.blog.entity.BlBlog;
import com.ferry.blog.entity.BlType;
import com.ferry.blog.mapper.BlBlogMapper;
import com.ferry.blog.service.BlogService;
import com.ferry.common.utils.IdWorker;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
@Service
public class BlogServiceImpl extends ServiceImpl <BlBlogMapper, BlBlog> implements BlogService {

    @Autowired
    private BlBlogMapper blogMapper;

    @Autowired
    private IdWorker idWorker;
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Page <BlBlog> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        String label = pageRequest.getParamValue("name");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isBlank(label),"title", label);
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
    public boolean saveType(BlBlog blBlog) {
        if(blBlog.getId() != null) {
            BlBlog oldBlog = blogMapper.selectById(blBlog.getId());
            if (oldBlog != null) {
                oldBlog.setUpdateTime(new Date());
                oldBlog.setSort(blBlog.getSort());
                oldBlog.setTitle(blBlog.getTitle());
                oldBlog.setSummary(blBlog.getSummary());
                oldBlog.setContent(blBlog.getContent());
                blogMapper.updateById(oldBlog);
            }
        } else {
            blBlog.setId(idWorker.nextId()+"");
            blBlog.setCreateTime(new Date());
            blogMapper.insert(blBlog);
        }
        return true;
    }
}
