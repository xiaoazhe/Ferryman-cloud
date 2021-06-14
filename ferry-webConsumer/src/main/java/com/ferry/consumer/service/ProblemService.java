package com.ferry.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.consumer.http.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlProblem;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface ProblemService extends IService <BlProblem> {

    PageResult getIndividualPro(PageRequest pageRequest);

    PageResult newlist(Integer labelId, PageRequest pageRequest);

    PageResult hotlist(Integer labelId, PageRequest pageRequest);

    PageResult waitlist(Integer labelId, PageRequest pageRequest);

    BlProblem getProById(String id);

    String savePro(BlProblem problem);

    String deleteById(String id);

    String setGood(String id);

    String setCollect(String id, Integer statusId);

    PageResult getCollect(Integer statusId, PageRequest pageRequest);

    String deleteCollect(String id, Integer statusId);

    List <BlProblem> getSimilarById(String id);

    String delete();
}
