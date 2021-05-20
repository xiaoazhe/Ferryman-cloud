package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.server.admin.entity.SysDept;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
public interface SysDeptService extends IService <SysDept> {

    int delete(SysDept record);

    int delete(List <SysDept> records);

    PageResult findPage(PageRequest pageRequest);

    List<SysDept> findTree();

}
