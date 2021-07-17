package com.ferry.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.blog.dto.FileConfig;
import com.ferry.core.file.entity.Config;
import com.ferry.server.blog.entity.BlFile;
import com.ferry.core.http.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
public interface FileService extends IService <BlFile> {

    String uploadFile(MultipartFile image);

    String fileConfig(FileConfig fileConfig);

    List<FileConfig> getFileConfig();
}
