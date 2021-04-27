package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.admin.entity.SysLoginLog;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
public interface SysLoginLogService extends IService <SysLoginLog> {

    int delete(SysLoginLog record);

    int delete(List <SysLoginLog> records);

    PageResult findPage(PageRequest pageRequest);

    int writeLoginLog(String userName, String ip);
}
