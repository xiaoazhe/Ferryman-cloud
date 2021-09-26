package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.admin.vo.NotifyVo;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.admin.entity.SysNotify;

import java.util.List;
import java.util.Map;

/**
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
public interface SysNotifyService extends IService <SysNotify> {

    String saveOrUpdateNotify(NotifyVo notify);

    String readNotify(String id);

    PageResult findPage(PageRequest pageRequest);

    List<SysNotify> getNoReadListByUserId();

    PageResult getNotifyByType(int pageNum, int pageSize, String title, String type);

    Map getNotifyById(String id);
}
