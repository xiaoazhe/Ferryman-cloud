package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.service.SysNotifyService;
import com.ferry.admin.util.SecurityUtils;
import com.ferry.admin.vo.NotifyVo;
import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.admin.entity.SysLog;
import com.ferry.server.admin.entity.SysNotify;
import com.ferry.server.admin.entity.SysNotifyRecord;
import com.ferry.server.admin.mapper.SysNotifyMapper;
import com.ferry.server.admin.mapper.SysNotifyRecordMapper;
import com.ferry.server.admin.mapper.SysUserMapper;
import com.ferry.server.blog.entity.BlProLabel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
public class SysNotifyServiceImpl extends ServiceImpl <SysNotifyMapper, SysNotify> implements SysNotifyService {

    @Autowired
    private SysNotifyMapper sysNotifyMapper;

    @Autowired
    private SysNotifyRecordMapper sysNotifyRecordMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

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

    @Override
    public String readNotify(Integer id) {
        SysNotifyRecord sysNotifyRecord = sysNotifyRecordMapper.selectById(id);
        sysNotifyRecord.setIsRead(1);
        sysNotifyRecord.setLastUpdateTime(new Date());
        Integer update = sysNotifyRecordMapper.updateById(sysNotifyRecord);
        if (update == null) {
            return StateEnums.SAVEBLOG_ERR.getMsg();
        }
        return StateEnums.SAVEBLOG_SUC.getMsg();
    }

    /**
     * 创建人和接受人才会查询到
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        String title = pageRequest.getParamValue(FieldStatusEnum.USERNAME);
        String userName = SecurityUtils.getUsername();
        Long userId = sysUserMapper.findByName(userName).getId();
        QueryWrapper<SysNotifyRecord> query = new QueryWrapper<SysNotifyRecord>();
        query.eq(SysNotifyRecord.COL_CREATE_BY, userName)
                .or()
                .eq(SysNotifyRecord.COL_USER_ID, userId);
        List<Long> idList = sysNotifyRecordMapper.selectList(query).stream().map(SysNotifyRecord::getNotifyId).collect(Collectors.toList());
        Page<SysNotify> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<SysNotify> queryWrapper = new QueryWrapper<SysNotify>();
        queryWrapper.eq(!StringUtils.isBlank(title), SysNotify.COL_TITLE, title);
        queryWrapper.in(SysNotify.COL_ID, idList);
        Page<SysNotify> userIPage = sysNotifyMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(userIPage);
        return pageResult;
    }
}
