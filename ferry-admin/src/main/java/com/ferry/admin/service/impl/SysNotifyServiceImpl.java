package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.service.SysNotifyService;
import com.ferry.admin.vo.NotifyVo;
import com.ferry.common.enums.StateEnums;
import com.ferry.server.admin.entity.SysNotify;
import com.ferry.server.admin.entity.SysNotifyRecord;
import com.ferry.server.admin.mapper.SysNotifyMapper;
import com.ferry.server.admin.mapper.SysNotifyRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
public class SysNotifyServiceImpl extends ServiceImpl <SysNotifyMapper, SysNotify> implements SysNotifyService {

    @Autowired
    private SysNotifyMapper sysNotifyMapper;

    @Autowired
    private SysNotifyRecordMapper sysNotifyRecordMapper;

    @Override
    public String saveOrUpdateNotify(NotifyVo notify) {
        SysNotify sysNotify = new SysNotify();
        sysNotify.setContent(notify.getContent());
        sysNotify.setDelFlag("0");
        sysNotify.setFiles(notify.getFiles());
        sysNotify.setRemarks(notify.getRemarks());
        sysNotify.setSmsRemind(notify.getSmsRemind());
        sysNotify.setTitle(notify.getTitle());
        sysNotify.setType(notify.getType());
        sysNotify.setStatus(notify.getStatus());
        SysNotifyRecord sysNotifyRecord = new SysNotifyRecord();
        sysNotifyRecord.setIsRead(0);
        sysNotifyRecord.setUserId(notify.getUserId());
        if (notify.getId() == null) {
            int notifyId = sysNotifyMapper.insert(sysNotify);
            sysNotifyRecord.setNotifyId(Long.valueOf(notifyId));
            return StateEnums.SAVEBLOG_SUC.getMsg();
        } else {
            sysNotifyRecord.setNotifyId(notify.getId());
            sysNotifyMapper.updateById(sysNotify);
            sysNotifyRecordMapper.updateById(sysNotifyRecord);
            return StateEnums.SAVEBLOG_ERR.getMsg();
        }
    }
}
