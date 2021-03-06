package com.ferry.file.service.impl;

import com.ferry.core.file.FileUploader;
import com.ferry.core.file.GlobalFileUploader;
import com.ferry.core.file.emums.FileUploadType;
import com.ferry.core.file.entity.VirtualFile;
import com.ferry.core.http.Result;
import com.ferry.file.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public Result uploadFile(MultipartFile image) {
        String imgUrl = null;
        try {
            imgUrl = uploader(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Result result = new Result();
//        result.setData("http://azhe.oss-cn-beijing.aliyuncs.com/oneblog/20210509124220353.jpg");
        result.setData(imgUrl);
        return result;
    }
    public String uploader(MultipartFile multipartFile) {
        FileUploader uploader = new GlobalFileUploader();
        VirtualFile virtualFile = uploader.upload(multipartFile, FileUploadType.COMMON.getPath(), true);
        return virtualFile.getFullFilePath();
    }
}
