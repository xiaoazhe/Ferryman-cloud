package com.ferry.consumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.DateTimeUtils;
import com.ferry.common.utils.IdWorker;
import com.ferry.common.utils.PoiUtils;
import com.ferry.common.utils.StringUtils;
import com.ferry.consumer.service.SysUserService;
import com.ferry.consumer.utils.PasswordUtils;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.admin.entity.SysRole;
import com.ferry.server.admin.entity.SysUser;
import com.ferry.server.admin.entity.SysUserRole;
import com.ferry.server.admin.mapper.SysRoleMapper;
import com.ferry.server.admin.mapper.SysUserMapper;
import com.ferry.server.admin.mapper.SysUserRoleMapper;
import com.ferry.server.blog.entity.BlUser;
import com.ferry.server.blog.mapper.BlUserMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Service
public class SysUserServiceImpl extends ServiceImpl <SysUserMapper, SysUser> implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Resource
	private BlUserMapper userMapper;
	@Autowired
	private IdWorker idWorker;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public boolean save(SysUser record) {
		Long id = null;
		if(record.getId() == null || record.getId() == 0) {
			// ????????????
			sysUserMapper.insert(record);
			id = record.getId();
		} else {
			// ??????????????????
			sysUserMapper.updateById(record);
		}
		// ??????????????????
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
	public PageResult findPage(PageRequest pageRequest) {
		Page<SysUser> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
		String name = pageRequest.getParamValue("name");
		String email = pageRequest.getParamValue("email");
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.like(!Objects.isNull(name),"name",name);
		queryWrapper.like(!Objects.isNull(email),"email",email);
		Page<SysUser> userIPage = sysUserMapper.selectPage(page, queryWrapper);
		PageResult pageResult = new PageResult(userIPage);
		// ????????????????????????
		findUserRoles(pageResult);
		return pageResult;
	}
	
	/**
	 * ??????????????????
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
	public List<SysUserRole> findUserRoles(Long userId) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq(SysUserRole.COL_USER_ID, userId);
		return sysUserRoleMapper.selectList(queryWrapper);
	}
	
	@Override
	public File createUserExcelFile(PageRequest pageRequest) {
		PageResult pageResult = findPage(pageRequest);
		return createUserExcelFile(pageResult.getContent());
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
		row0.createCell(++columnIndex).setCellValue("?????????");
		row0.createCell(++columnIndex).setCellValue("??????");
		row0.createCell(++columnIndex).setCellValue("??????");
		row0.createCell(++columnIndex).setCellValue("??????");
		row0.createCell(++columnIndex).setCellValue("??????");
		row0.createCell(++columnIndex).setCellValue("?????????");
		row0.createCell(++columnIndex).setCellValue("??????");
		row0.createCell(++columnIndex).setCellValue("??????");
		row0.createCell(++columnIndex).setCellValue("?????????");
		row0.createCell(++columnIndex).setCellValue("????????????");
		row0.createCell(++columnIndex).setCellValue("???????????????");
		row0.createCell(++columnIndex).setCellValue("??????????????????");
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

	public BlUser login(String mobile, String password) {
		QueryWrapper<BlUser> queryWrapper = new QueryWrapper <>();
		queryWrapper.eq("mobile", mobile);
		BlUser user = userMapper.selectOne(queryWrapper);
		if(user != null && encoder.matches(password, user.getPassword())){
			return user;
		}
		SysUser sysUser = sysUserMapper.findByMobile(mobile);
		// ??????????????????????????????
		if (sysUser == null) {
			return null;
		}
		if (!PasswordUtils.matches(sysUser.getSalt(), password, sysUser.getPassword())) {
			return null;
		}
		BlUser newUser = new BlUser();
		newUser.setMobile(sysUser.getMobile());
		newUser.setEmail(sysUser.getEmail());
		newUser.setPassword(sysUser.getPwd());
		newUser.setNickname(sysUser.getName());
		newUser.setAvatar(sysUser.getAvatar());
		add(newUser);
		return newUser;
	}

	@Override
	public String add(BlUser user) {
		if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getNickname()) ||
				StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getMobile())) {
			return StateEnums.PARAMETER_ERROR.getMsg();
		}
		user.setId(idWorker.nextId() + "");
		//????????????
		user.setPassword(encoder.encode(user.getPassword()));
		user.setFollowcount(0);//?????????
		user.setFanscount(0);//?????????
		user.setOnline(0L);//????????????
		user.setRegdate(new Date());//????????????
		user.setUpdateTime(new Date());//????????????
		user.setLastdate(new Date());//??????????????????
		userMapper.insert(user);
		return StateEnums.REGISTER_SUC.getMsg();
	}

}
