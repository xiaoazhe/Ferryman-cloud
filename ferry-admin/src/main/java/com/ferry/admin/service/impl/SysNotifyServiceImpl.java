package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.service.SysNotifyService;
import com.ferry.admin.util.SecurityUtils;
import com.ferry.admin.vo.NotifyVo;
import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.common.enums.NotifyType;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.IdWorker;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.admin.entity.SysNotify;
import com.ferry.server.admin.entity.SysNotifyRecord;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.server.admin.mapper.SysNotifyMapper;
import com.ferry.server.admin.mapper.SysNotifyRecordMapper;
import com.ferry.server.admin.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
@Service
public class SysNotifyServiceImpl extends ServiceImpl <SysNotifyMapper, SysNotify> implements SysNotifyService {

    @Resource
    private SysNotifyMapper sysNotifyMapper;

    @Resource
    private SysNotifyRecordMapper sysNotifyRecordMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private IdWorker idWorker;

    @Override
    public String saveOrUpdateNotify(NotifyVo notify) {
        String userName = SecurityUtils.getUsername();
        String userId = String.valueOf(sysUserMapper.findByName(userName).getId());
        SysNotify sysNotify = new SysNotify();
        sysNotify.setContent(notify.getContent());
        sysNotify.setDelFlag("0");
        sysNotify.setFiles(notify.getFiles());
        sysNotify.setRemarks(notify.getRemarks());
        sysNotify.setSmsRemind(notify.getSmsRemind());
        sysNotify.setTitle(notify.getTitle());
        sysNotify.setType(notify.getType());
        sysNotify.setStatus(notify.getStatus());
        sysNotify.setIsRead(0);
        sysNotify.setCreateUserId(userId);
        if (StringUtils.isBlank(notify.getId())) {
            String id = idWorker.nextId() + "";
            sysNotify.setId(id);
            sysNotifyMapper.insert(sysNotify);
            for (String userIds : notify.getUserIds()) {
                SysNotifyRecord sysNotifyRecord = new SysNotifyRecord();
                sysNotifyRecord.setIsRead(0);
                sysNotifyRecord.setUserId(Long.valueOf(userIds));
                sysNotifyRecord.setNotifyId(id);
                sysNotifyRecordMapper.insert(sysNotifyRecord);
            }
            return StateEnums.SAVEBLOG_SUC.getMsg();
        } else {
            sysNotifyMapper.updateById(sysNotify);
            for (String userIds : notify.getUserIds()) {
                HashMap map = new HashMap();
                map.put(SysNotifyRecord.COL_USER_ID, userIds);
                map.put(SysNotifyRecord.COL_NOTIFY_ID, notify.getId());
                sysNotifyRecordMapper.deleteByMap(map);
                SysNotifyRecord sysNotifyRecord = new SysNotifyRecord();
                sysNotifyRecord.setIsRead(0);
                sysNotifyRecord.setUserId(Long.valueOf(userIds));
                sysNotifyRecord.setNotifyId(sysNotify.getId());
                sysNotifyRecordMapper.insert(sysNotifyRecord);
            }
            return StateEnums.SAVEBLOG_SUC.getMsg();
        }
    }

    @Override
    public String readNotify(String id) {
        SysNotify sysNotify = sysNotifyMapper.selectById(id);
        sysNotify.setIsRead(1);
        sysNotify.setReadDate(new Date());
        Integer update = sysNotifyMapper.updateById(sysNotify);
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
        Page<SysNotify> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<SysNotify> queryWrapper = new QueryWrapper<SysNotify>();
        queryWrapper.eq(!StringUtils.isBlank(title), SysNotify.COL_TITLE, title);
        queryWrapper.eq(SysNotify.CRESARE_USER_ID, userId).or().eq(SysNotify.USER_ID, userId);
        Page<SysNotify> userIPage = sysNotifyMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(userIPage);
        return pageResult;
    }

    @Override
    public List<SysNotify> getNoReadListByUserId() {
        String userName = SecurityUtils.getUsername();
        Long userId = sysUserMapper.findByName(userName).getId();
        QueryWrapper<SysNotify> query = new QueryWrapper<SysNotify>();
        query.eq(SysNotify.CRESARE_USER_ID, userId);
        query.eq(SysNotify.IS_READ, 0l);
        return sysNotifyMapper.selectList(query);
    }

    @Override
    public PageResult getNotifyByType(int pageNum, int pageSize, String title, String type) {
        Page<SysNotify> page = new Page<>(pageNum, pageSize);
        String userName = SecurityUtils.getUsername();
        Long userId = sysUserMapper.findByName(userName).getId();
        QueryWrapper<SysNotify> query = new QueryWrapper<SysNotify>();
        if (Objects.equals(type, NotifyType.SEND_NOTIFY.name())) {
            query.eq(SysNotify.CRESARE_USER_ID, userId);
        } else {
            query.eq(SysNotify.USER_ID, userId);
        }
        query.eq(SysNotify.CRESARE_USER_ID, userId);
        query.eq(SysNotify.IS_READ, 0l);
        query.eq(!StringUtils.isBlank(title), SysNotify.COL_TITLE, title);
        Page<SysNotify> userIPage = sysNotifyMapper.selectPage(page, query);
        PageResult pageResult = new PageResult(userIPage);
        return pageResult;
    }

    @Override
    public Map getNotifyById(String id) {
        HashMap map = new HashMap(16);
        SysNotify notify = sysNotifyMapper.selectById(id);
        List<Long> list = new LinkedList();
        QueryWrapper<SysNotifyRecord> queryWrapper = new QueryWrapper();
        queryWrapper.eq(SysNotifyRecord.COL_NOTIFY_ID, notify.getId());
        list = sysNotifyRecordMapper.selectList(queryWrapper).stream().map(SysNotifyRecord::getUserId)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new RuntimeException("没有通知人");
        }
        QueryWrapper<SysUser> userQuery = new QueryWrapper();
        userQuery.in(SysUser.COL_ID, list);
        List<SysUser> userList = sysUserMapper.selectList(userQuery);
        map.put("notify", notify);
        map.put("users", userList);
        return map;
    }
}
