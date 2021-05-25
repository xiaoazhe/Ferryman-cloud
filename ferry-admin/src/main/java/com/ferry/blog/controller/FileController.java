package com.ferry.blog.controller;

import com.ferry.blog.service.FileService;
import com.ferry.blog.service.impl.UploadService;
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
    @Autowired
    private UploadService uploadService;

    /**
     * 阿里云 七牛云 文件上传 限图片
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/images")
    public Result addFile(MultipartFile file) throws Exception {
        return fileService.uploadFile(file);
    }

    /**
     * fdfs文件服务器上传文件  不限格式
     * @param file
     * @return
     */
    @PostMapping(value = "/fdfsUploadImage")
    public Result fdfsUploadImage(MultipartFile file) {
        String url = uploadService.uploadImage(file);
        Result result = new Result();
        result.setData(url);
        return result;
    }


    @PostMapping(value = "/deletefdfsImage")
    public Result deletefdfsImage(String url) {
        Result result = uploadService.deletefdfsImage(url);
        return result;
    }
}
