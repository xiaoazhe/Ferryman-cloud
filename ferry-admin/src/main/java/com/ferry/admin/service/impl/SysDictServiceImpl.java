package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.server.admin.entity.SysDict;
import com.ferry.server.admin.mapper.SysDictMapper;
import com.ferry.admin.service.SysDictService;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictServiceImpl extends ServiceImpl <SysDictMapper, SysDict> implements SysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	public boolean save(SysDict record) {
		if(record.getId() == null || record.getId() == 0) {
			sysDictMapper.insert(record);
			return true;
		}
		sysDictMapper.updateById(record);
		return true;
	}

	@Override
	public int delete(SysDict record) {
		return sysDictMapper.deleteById(record.getId());
	}

	@Override
	public int delete(List<SysDict> records) {
		for(SysDict record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		String label = pageRequest.getParamValue(FieldStatusEnum.LABEL);
		Page <SysDict> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.like(!StringUtils.isBlank(label), SysDict.COL_LABEL, label);
		Page<SysDict> userIPage = sysDictMapper.selectPage(page, queryWrapper);
		PageResult pageResult = new PageResult(userIPage);
		return pageResult;
	}

	@Override
	public List<SysDict> findByLable(String lable) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq(!StringUtils.isBlank(lable), SysDict.COL_LABEL, lable);
		return sysDictMapper.selectList(queryWrapper);
	}

}
