package com.ferry.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.admin.vo.UserInfoVo;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.server.admin.entity.SysUserRole;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户管理
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
public interface SysUserService extends IService <SysUser> {

	Map findIntro();

	boolean save(SysUser record);

	int delete(SysUser record);

	int delete(List<SysUser> records);

	SysUser findById(Long id);

	SysUser findByName(String username);

	PageResult findPage(PageRequest pageRequest);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

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

	List<UserInfoVo> list(UserInfoVo userInfo);

	String deleteAvatarById(Integer id);
}
