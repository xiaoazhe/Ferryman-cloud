package com.ferry.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlBlog;


import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
public interface BlogService extends IService <BlBlog> {

    PageResult findPage(PageRequest pageRequest);

    boolean removeTypes(List <BlBlog> blBlogs);

    Result saveBlog(BlBlog blog);

    String deleteById(String id);

    Result selectById(String id);

    Result hotBlog();
}
