package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.server.admin.entity.SysLoginLog;
import com.ferry.server.admin.mapper.SysLoginLogMapper;
import com.ferry.admin.service.SysLoginLogService;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysLoginLogServiceImpl extends ServiceImpl <SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;

	@Override
	public boolean save(SysLoginLog record) {
		if(record.getId() == null || record.getId() == 0) {
			sysLoginLogMapper.insert(record);
			return true;
		}
		sysLoginLogMapper.updateById(record);
		return true;
	}

	@Override
	public int delete(SysLoginLog record) {
		return sysLoginLogMapper.deleteById(record.getId());
	}

	@Override
	public int delete(List<SysLoginLog> records) {
		for(SysLoginLog record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		String userName = pageRequest.getParamValue(FieldStatusEnum.USERNAME);
		String status = pageRequest.getParamValue(FieldStatusEnum.STATUS);
		Page <SysLoginLog> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq(!StringUtils.isBlank(userName), SysLoginLog.COL_USER_NAME, userName);
		queryWrapper.eq(!StringUtils.isBlank(status), SysLoginLog.COL_STATUS, status);
		Page<SysLoginLog> userIPage = sysLoginLogMapper.selectPage(page, queryWrapper);
		PageResult pageResult = new PageResult(userIPage);
		return pageResult;
	}
	
	@Transactional
	@Override
	public int writeLoginLog(String userName, String ip) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq(SysLoginLog.COL_USER_NAME, userName);
		queryWrapper.eq(SysLoginLog.COL_STATUS, SysLoginLog.STATUS_ONLINE);
		List<SysLoginLog> sysLoginLogs = sysLoginLogMapper.selectList(queryWrapper);
		for(SysLoginLog sysLoginLog:sysLoginLogs) {
			sysLoginLog.setStatus(SysLoginLog.STATUS_LOGIN);
			sysLoginLogMapper.updateById(sysLoginLog);
		}
		SysLoginLog record = new SysLoginLog();
		record.setUserName(userName);
		record.setIp(ip);
		record.setStatus(SysLoginLog.STATUS_LOGOUT);
		sysLoginLogMapper.insert(record);
		record.setStatus(SysLoginLog.STATUS_ONLINE);
		sysLoginLogMapper.insert(record);
		return 0;
	}
}
