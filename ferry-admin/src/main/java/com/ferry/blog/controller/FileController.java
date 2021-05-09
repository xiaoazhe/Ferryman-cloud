package com.ferry.blog.controller;

import com.ferry.blog.service.FileService;
import com.ferry.core.http.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/images")
    public Result addFile(MultipartFile file) throws Exception {
        return fileService.uploadFile(file);
    }
}
