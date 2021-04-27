package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.admin.entity.SysLog;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
public interface SysLogService extends IService <SysLog> {

    int delete(SysLog record);

    int delete(List <SysLog> records);

    PageResult findPage(PageRequest pageRequest);
}
