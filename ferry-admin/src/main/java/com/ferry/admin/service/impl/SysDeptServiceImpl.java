package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.server.admin.entity.SysDept;
import com.ferry.server.admin.mapper.SysDeptMapper;
import com.ferry.admin.service.SysDeptService;
import com.ferry.core.page.MybatisPageHelper;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl extends ServiceImpl <SysDeptMapper, SysDept> implements SysDeptService {

	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Override
	public boolean save(SysDept record) {
		if(record.getId() == null || record.getId() == 0) {
			sysDeptMapper.insert(record);
			return true;
		}
		sysDeptMapper.updateById(record);
		return true;
	}

	@Override
	public int delete(SysDept record) {
		return sysDeptMapper.deleteById(record.getId());
	}

	@Override
	public int delete(List<SysDept> records) {
		for(SysDept record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysDeptMapper);
	}

	@Override
	public List<SysDept> findTree() {
		List<SysDept> sysDepts = new ArrayList<>();
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.ne(SysDept.COL_ID, 0);
		List<SysDept> depts = sysDeptMapper.selectList(queryWrapper);
		for (SysDept dept : depts) {
			if (dept.getParentId() == null || dept.getParentId() == 0) {
				dept.setLevel(0);
				sysDepts.add(dept);
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}

	private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
		for (SysDept sysDept : sysDepts) {
			List<SysDept> children = new ArrayList<>();
			for (SysDept dept : depts) {
				if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
					dept.setParentName(sysDept.getName());
					dept.setLevel(sysDept.getLevel() + 1);
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}
}
