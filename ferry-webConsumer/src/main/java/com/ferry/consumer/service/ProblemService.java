package com.ferry.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.consumer.http.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlProblem;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface ProblemService extends IService <BlProblem> {

    PageResult newlist(Integer labelId, PageRequest pageRequest);

    PageResult hotlist(Integer labelId, PageRequest pageRequest);

    PageResult waitlist(Integer labelId, PageRequest pageRequest);

    BlProblem getProById(String id);
}
