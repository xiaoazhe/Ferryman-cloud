package com.ferry.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.server.blog.entity.BlType;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
public interface TypeService extends IService <BlType> {

    PageResult findPage(PageRequest pageRequest);

    boolean removeTypes(List<BlType> types);

    boolean saveType(BlType type);

    List<BlType> findAll();

    BlType findById(String id);
}
