package com.ferry.core.file;


import com.ferry.core.file.entity.VirtualFile;
import com.ferry.core.file.exception.exception.GlobalFileException;
import com.ferry.core.file.upload.ApiClient;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;


public class GlobalFileUploader extends BaseFileUploader implements FileUploader {

    @Override
    public VirtualFile upload(InputStream is, String uploadType, String imageUrl, boolean save) {
        ApiClient apiClient = this.getApiClient(uploadType);
        VirtualFile virtualFile = apiClient.uploadImg(is, imageUrl);
        return this.saveFile(virtualFile, save, uploadType);
    }

    @Override
    public VirtualFile upload(File file, String uploadType, boolean save) {
        ApiClient apiClient = this.getApiClient(uploadType);
        VirtualFile virtualFile = apiClient.uploadImg(file);
        return this.saveFile(virtualFile, save, uploadType);
    }

    @Override
    public VirtualFile upload(MultipartFile file, String uploadType, boolean save) {
        ApiClient apiClient = this.getApiClient(uploadType);
        VirtualFile virtualFile = apiClient.uploadImg(file);
        return this.saveFile(virtualFile, save, uploadType);
    }

    @Override
    public boolean delete(String filePath, String uploadType) {
        if (StringUtils.isEmpty(filePath)) {
            throw new GlobalFileException("[文件服务]文件删除失败，文件为空！");
        }

        ApiClient apiClient = this.getApiClient(uploadType);
        return this.removeFile(apiClient, filePath, uploadType);
    }
}
