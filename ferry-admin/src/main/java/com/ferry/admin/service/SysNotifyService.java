package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.admin.vo.NotifyVo;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.admin.entity.SysNotify;

/**
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
public interface SysNotifyService extends IService <SysNotify> {

    String saveOrUpdateNotify(NotifyVo notify);

    String readNotify(Integer id);

    PageResult findPage(PageRequest pageRequest);
}
