package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.entity.SysDict;
import com.ferry.admin.entity.SysLog;
import com.ferry.admin.mapper.SysLogMapper;
import com.ferry.admin.service.SysLogService;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl extends ServiceImpl <SysLogMapper, SysLog> implements SysLogService {

	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public boolean save(SysLog record) {
		if(record.getId() == null || record.getId() == 0) {
			sysLogMapper.insert(record);
			return true;
		}
		sysLogMapper.updateById(record);
		return true;
	}

	@Override
	public int delete(SysLog record) {
		return sysLogMapper.deleteById(record.getId());
	}

	@Override
	public int delete(List<SysLog> records) {
		for(SysLog record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		String userName = pageRequest.getParamValue("userName");
		Page <SysLog> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq(!StringUtils.isBlank(userName),"user_Name",userName);
		Page<SysLog> userIPage = sysLogMapper.selectPage(page, queryWrapper);
		PageResult pageResult = new PageResult(userIPage);
		return pageResult;
	}
	
}
