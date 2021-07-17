package com.ferry.blog.controller;

import com.ferry.blog.dto.FileConfig;
import com.ferry.blog.service.FileService;
import com.ferry.blog.service.impl.UploadService;
import com.ferry.core.http.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
     * 阿里云 七牛云 文件上传 配置
     * @param fileConfig
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/fileConfig")
    public Result fileConfig(@RequestBody FileConfig fileConfig) {
        return Result.ok(fileService.fileConfig(fileConfig));
    }

    /**
     * 获取阿里云 七牛云 文件上传 配置
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getFileConfig")
    public Result getFileConfig() {
        return Result.ok(fileService.getFileConfig());
    }

    /**
     * 阿里云 七牛云 文件上传 限图片
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/images")
    public Result addFile(MultipartFile file) throws Exception {
        return Result.ok(fileService.uploadFile(file));
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
        return Result.ok(uploadService.deletefdfsImage(url));
    }
}
