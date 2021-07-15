package com.ferry.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.server.admin.entity.SysUserRole;
import com.ferry.server.blog.entity.BlUser;

import java.io.File;
import java.util.List;

/**
 * 用户管理
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
public interface SysUserService extends IService <SysUser> {

	boolean save(SysUser record);

	int delete(SysUser record);

	int delete(List <SysUser> records);

	SysUser findById(Long id);

	PageResult findPage(PageRequest pageRequest);

	/**
	 * 查找用户的角色集合
	 * @param userId
	 * @return
	 */
	List <SysUserRole> findUserRoles(Long userId);

	/**
	 * 生成用户信息Excel文件
	 * @param pageRequest 要导出的分页查询参数
	 * @return
	 */
	File createUserExcelFile(PageRequest pageRequest);

	public BlUser login(String mobile, String password);

	public void add(BlUser user);
}
