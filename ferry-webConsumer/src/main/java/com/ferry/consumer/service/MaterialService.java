package com.ferry.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.consumer.http.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlMaterial;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/17
 */
public interface MaterialService extends IService <BlMaterial> {

    PageResult findPage(PageRequest pageRequest);

    BlMaterial findById(String id);
}
