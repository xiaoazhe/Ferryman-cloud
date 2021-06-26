package com.ferry.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlProblem;


/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface ProblemService extends IService <BlProblem> {

    PageResult newlist(PageRequest pageRequest);

    String deleteById(String id);
}
