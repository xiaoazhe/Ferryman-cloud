package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.exception.ServeException;
import com.ferry.admin.util.FaceAiUtil;
import com.ferry.admin.util.SecurityUtils;
import com.ferry.common.enums.CommonStatusEnum;
import com.ferry.common.enums.FieldStatusEnum;
import com.ferry.common.enums.StateEnums;
import com.ferry.server.admin.entity.*;
import com.ferry.server.admin.mapper.SysDeptMapper;
import com.ferry.server.admin.mapper.SysRoleMapper;
import com.ferry.server.admin.mapper.SysUserMapper;
import com.ferry.server.admin.mapper.SysUserRoleMapper;
import com.ferry.admin.service.SysMenuService;
import com.ferry.admin.service.SysUserService;
import com.ferry.common.utils.DateTimeUtils;
import com.ferry.common.utils.PoiUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlBlog;
import com.ferry.server.blog.mapper.BlBlogMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service
public class SysUserServiceImpl extends ServiceImpl <SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysDeptMapper deptMapper;
	@Autowired
	private FaceAiUtil faceAiUtil;
	@Autowired
	private BlBlogMapper blBlogMapper;

	@Override
	public Map findIntro() {
		HashMap map = new HashMap();
		SysUser user = sysUserMapper.findByName(SecurityUtils.getUsername());
		Integer blogClick = blBlogMapper.getClickByCreantName(user.getName());
		Integer blogCollect = blBlogMapper.getCollectByCreantName(user.getName());
		Integer material = blBlogMapper.getMaterialByCreantName(user.getName());
		QueryWrapper<BlBlog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(BlBlog.COL_CREATE_BY, user.getName());
		queryWrapper.orderByDesc(BlBlog.COL_CREATE_TIME);
		List<BlBlog> blogList = blBlogMapper.selectList(queryWrapper);
		map.put("blogClick", blogClick);
		map.put("blogCollect", blogCollect);
		map.put("material", material);
		map.put("blogSize", blogList.size());
		map.put("user", user);
		map.put("blogList", blogList);
		return map;
	}

	@Transactional
	@Override
	public boolean save(SysUser record) {
		Long id = null;
		if(record.getId() == null || record.getId() == 0) {
			// 新增用户
			sysUserMapper.insert(record);
			id = record.getId();
			if (record.getAvatar() != null) {
				faceAiUtil.faceRegister(String.valueOf(record.getId()), record.getAvatar(), false);
			}
		} else {
			// 更新用户信息
			sysUserMapper.updateById(record);
			if (record.getAvatar() != null) {
				faceAiUtil.faceUpdate(String.valueOf(record.getId()), record.getAvatar(), false);
			}
		}
		// 更新用户角色
		if(id != null && id == 0) {
			return true;
		}
		if(id != null) {
			for(SysUserRole sysUserRole:record.getUserRoles()) {
				sysUserRole.setUserId(id);
			}
		} else {
			sysUserRoleMapper.deleteById(record.getId());
		}
		for(SysUserRole sysUserRole:record.getUserRoles()) {
			sysUserRoleMapper.insert(sysUserRole);
		}
		return true;
	}

	@Override
	public int delete(SysUser record) {
		return sysUserMapper.deleteById(record.getId());
	}

	@Override
	public int delete(List<SysUser> records) {
		for(SysUser record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysUser findById(Long id) {
		return sysUserMapper.selectById(id);
	}

	@Override
	public SysUser findByName(String name) {
		if (Objects.isNull(name)) {
			throw new ServeException(CommonStatusEnum.NAME_NOT_NULL);
		}
		return sysUserMapper.findByName(name);
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		Page<SysUser> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
		String name = pageRequest.getParamValue(FieldStatusEnum.NAME);
		String email = pageRequest.getParamValue(FieldStatusEnum.EMAIL);
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.like(!Objects.isNull(name), SysUser.COL_NAME, name);
		queryWrapper.like(!Objects.isNull(email), SysUser.COL_EMAIL, email);
		Page<SysUser> userIPage = sysUserMapper.selectPage(page, queryWrapper);
		for (SysUser sysUser: userIPage.getRecords()) {
			SysDept dept = deptMapper.selectById(sysUser.getDeptId());
			if (dept != null) {
				sysUser.setDeptName(dept.getName());
			}
		}
		PageResult pageResult = new PageResult(userIPage);
		// 加载用户角色信息
		findUserRoles(pageResult);
		return pageResult;
	}
	
	/**
	 * 加载用户角色
	 * @param pageResult
	 */
	private void findUserRoles(PageResult pageResult) {
		List<?> content = pageResult.getContent();
		for(Object object:content) {
			SysUser sysUser = (SysUser) object;
			List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
			sysUser.setUserRoles(userRoles);
			sysUser.setRoleNames(getRoleNames(userRoles));
		}
	}

	private String getRoleNames(List<SysUserRole> userRoles) {
		StringBuilder sb = new StringBuilder();
		for(Iterator<SysUserRole> iter=userRoles.iterator(); iter.hasNext();) {
			SysUserRole userRole = iter.next();
			SysRole sysRole = sysRoleMapper.selectById(userRole.getRoleId());
			if(sysRole == null) {
				continue ;
			}
			sb.append(sysRole.getRemark());
			if(iter.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	public Set<String> findPermissions(String userName) {	
		Set<String> perms = new HashSet<>();
		List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
		for(SysMenu sysMenu:sysMenus) {
			if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
				perms.add(sysMenu.getPerms());
			}
		}
		return perms;
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper();
		queryWrapper.eq(SysUserRole.COL_USER_ID, userId);
		return sysUserRoleMapper.selectList(queryWrapper);
	}
	
	@Override
	public File createUserExcelFile(PageRequest pageRequest) {
		PageResult pageResult = findPage(pageRequest);
		return createUserExcelFile(pageResult.getContent());
	}

	@Override
	public String deleteAvatarById(Integer id) {
		SysUser user = sysUserMapper.selectById(id);
		user.setAvatar("");
		user.setLastUpdateTime(new Date());
		sysUserMapper.updateById(user);
		return StateEnums.DELETED.getMsg();
	}

	public static File createUserExcelFile(List<?> records) {
		if (records == null) {
			records = new ArrayList<>();
		}
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row0 = sheet.createRow(0);
		int columnIndex = 0;
		row0.createCell(columnIndex).setCellValue("No");
		row0.createCell(++columnIndex).setCellValue("ID");
		row0.createCell(++columnIndex).setCellValue("用户名");
		row0.createCell(++columnIndex).setCellValue("昵称");
		row0.createCell(++columnIndex).setCellValue("机构");
		row0.createCell(++columnIndex).setCellValue("角色");
		row0.createCell(++columnIndex).setCellValue("邮箱");
		row0.createCell(++columnIndex).setCellValue("手机号");
		row0.createCell(++columnIndex).setCellValue("状态");
		row0.createCell(++columnIndex).setCellValue("头像");
		row0.createCell(++columnIndex).setCellValue("创建人");
		row0.createCell(++columnIndex).setCellValue("创建时间");
		row0.createCell(++columnIndex).setCellValue("最后更新人");
		row0.createCell(++columnIndex).setCellValue("最后更新时间");
		for (int i = 0; i < records.size(); i++) {
			SysUser user = (SysUser) records.get(i);
			Row row = sheet.createRow(i + 1);
			for (int j = 0; j < columnIndex + 1; j++) {
				row.createCell(j);
			}
			columnIndex = 0;
			row.getCell(columnIndex).setCellValue(i + 1);
			row.getCell(++columnIndex).setCellValue(user.getId());
			row.getCell(++columnIndex).setCellValue(user.getName());
			row.getCell(++columnIndex).setCellValue(user.getNickName());
			row.getCell(++columnIndex).setCellValue(user.getDeptName());
			row.getCell(++columnIndex).setCellValue(user.getRoleNames());
			row.getCell(++columnIndex).setCellValue(user.getEmail());
			row.getCell(++columnIndex).setCellValue(user.getStatus());
			row.getCell(++columnIndex).setCellValue(user.getAvatar());
			row.getCell(++columnIndex).setCellValue(user.getCreateBy());
			row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getCreateTime()));
			row.getCell(++columnIndex).setCellValue(user.getLastUpdateBy());
			row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getLastUpdateTime()));
		}
		return PoiUtils.createExcelFile(workbook, "download_user");
	}

}
