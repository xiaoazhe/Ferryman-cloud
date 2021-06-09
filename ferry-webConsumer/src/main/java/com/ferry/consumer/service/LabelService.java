package com.ferry.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.consumer.http.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlLabel;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface LabelService extends IService <BlLabel> {

    PageResult selectAllByUser(PageRequest pageRequest);

}
