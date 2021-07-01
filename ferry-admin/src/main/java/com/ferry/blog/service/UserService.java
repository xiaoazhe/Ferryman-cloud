package com.ferry.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlUser;

/**
 * @Author: 摆渡人
 * @Date: 2021/7/1
 */
public interface UserService extends IService <BlUser> {

    PageResult findPage(PageRequest pageRequest);
}
