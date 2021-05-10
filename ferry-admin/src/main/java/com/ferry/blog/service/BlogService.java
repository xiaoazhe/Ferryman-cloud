package com.ferry.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.blog.entity.BlBlog;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
public interface BlogService extends IService <BlBlog> {

    PageResult findPage(PageRequest pageRequest);

    boolean removeTypes(List <BlBlog> blBlogs);

    boolean saveBlog(BlBlog blog);
}