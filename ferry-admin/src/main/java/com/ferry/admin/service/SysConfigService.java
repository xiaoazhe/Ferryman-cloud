package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.admin.entity.SysConfig;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
public interface SysConfigService extends IService <SysConfig> {

    int delete(SysConfig record);

    int delete(List <SysConfig> records);

    PageResult findPage(PageRequest pageRequest);

    List<SysConfig> findByLable(String lable);
}
