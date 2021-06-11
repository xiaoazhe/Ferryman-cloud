package com.ferry.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.consumer.http.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlReply;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface ReplyService extends IService <BlReply> {

    List <BlReply> newlist();

    PageResult getByProId(String proId, PageRequest pageRequest);

    String add(BlReply reply);
}
