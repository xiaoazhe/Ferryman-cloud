package com.ferry.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.consumer.http.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlFriendLink;

import java.util.List;


/**
 * @Author: 摆渡人
 * @Date: 2021/6/17
 */
public interface FriendLinkService  extends IService <BlFriendLink> {

    String add(BlFriendLink friendLink);

    BlFriendLink findById(String id);

    PageResult findPage(PageRequest pageRequest);

    List <BlFriendLink> friendTop();
}
