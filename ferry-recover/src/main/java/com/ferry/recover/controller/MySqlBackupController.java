package com.ferry.recover.controller;

import com.ferry.recover.constants.BackupConstants;
import com.ferry.recover.datasource.BackupDataSourceProperties;
import com.ferry.recover.service.MysqlBackupService;
import com.ferry.recover.util.Result;
import com.ferry.common.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 备份服务
 * @Author: 摆渡人
 * @Date: 2021/4/27
 */
@Api("数据恢复模块")
@RestController
@RequestMapping("/backup")
public class MySqlBackupController {

	@Resource
	MysqlBackupService mysqlBackupService;
	@Autowired
	BackupDataSourceProperties properties;

	@ApiOperation(value = "备份")
	@GetMapping("/backup")
	public Result backup() {
		String backupFodlerName = BackupConstants.DEFAULT_BACKUP_NAME + "_" + (new SimpleDateFormat(BackupConstants.DATE_FORMAT)).format(new Date());
		return backup(backupFodlerName);
	}

	private Result backup(String backupFodlerName) {
		String host = properties.getHost();
		String userName = properties.getUserName();
		String password = properties.getPassword();
		String database = properties.getDatabase();
		String backupFolderPath = BackupConstants.BACKUP_FOLDER + backupFodlerName + File.separator;
		String fileName = BackupConstants.BACKUP_FILE_NAME;
		try {
			boolean success = mysqlBackupService.backup(host, userName, password, backupFolderPath, fileName, database);
			if(!success) {
				Result.error("数据备份失败");
			}
		} catch (Exception e) {
			return Result.error(500, e.getMessage());
		}
		return Result.ok();
	}

	@ApiOperation(value = "恢复")
	@GetMapping("/restore")
	public Result restore(@RequestParam String name) throws IOException {
		String host = properties.getHost();
		String userName = properties.getUserName();
		String password = properties.getPassword();
		String database = properties.getDatabase();
		String restoreFilePath = BackupConstants.RESTORE_FOLDER + name;
		try {
			mysqlBackupService.restore(restoreFilePath, host, userName, password, database);
		} catch (Exception e) {
			return Result.error(500, e.getMessage());
		}
		return Result.ok();
	}

	@ApiOperation(value = "查找备份文件")
	@GetMapping("/findRecords")
	public Result findBackupRecords() {
		if(!new File(BackupConstants.DEFAULT_RESTORE_FILE).exists()) {
			// 初始默认备份文件
			backup(BackupConstants.DEFAULT_BACKUP_NAME);
		}
		List<Map<String, String>> backupRecords = new ArrayList<>();
		File restoreFolderFile = new File(BackupConstants.RESTORE_FOLDER);
		if(restoreFolderFile.exists()) {
			for(File file:restoreFolderFile.listFiles()) {
				Map<String, String> backup = new HashMap<>();
				backup.put("name", file.getName());
				backup.put("title", file.getName());
				if(BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())) {
					backup.put("title", "系统默认备份");
				}
				backupRecords.add(backup);
			}
		}
		// 排序，默认备份最前，然后按时间戳排序，新备份在前面
		backupRecords.sort((o1, o2) -> BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o1.get("name")) ? -1
				: BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o2.get("name")) ? 1 : o2.get("name").compareTo(o1.get("name")));
		return Result.ok(backupRecords);
	}

	@ApiOperation(value = "删除备份")
	@GetMapping("/delete")
	public Result deleteBackupRecord(@RequestParam String name) {
		if(BackupConstants.DEFAULT_BACKUP_NAME.equals(name)) {   	
			return Result.error("系统默认备份无法删除!");
		}
		String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
		try {
			FileUtils.deleteFile(new File(restoreFilePath));
		} catch (Exception e) {
			return Result.error(500, e.getMessage());
		}
		return Result.ok();
	}

}
