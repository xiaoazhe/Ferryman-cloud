package com.ferry.file.service;

import com.ferry.core.http.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
public interface FileService {

    Result uploadFile(MultipartFile image);

}
