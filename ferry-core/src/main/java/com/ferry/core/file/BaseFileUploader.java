package com.ferry.core.file;


import com.ferry.core.file.emums.ConfigKeyEnum;
import com.ferry.core.file.entity.File;
import com.ferry.core.file.entity.User;
import com.ferry.core.file.entity.VirtualFile;
import com.ferry.core.file.entity.bean.BlFile;
import com.ferry.core.file.exception.ZhydException;
import com.ferry.core.file.exception.exception.GlobalFileException;
import com.ferry.core.file.holder.SpringContextHolder;
import com.ferry.core.file.service.BizFileService;
import com.ferry.core.file.service.ConfigService;
import com.ferry.core.file.upload.AliyunOssApiClient;
import com.ferry.core.file.upload.ApiClient;
import com.ferry.core.file.upload.QiniuApiClient;
import com.ferry.core.file.util.BeanConvertUtil;
import com.ferry.core.file.util.SessionUtil;
import org.springframework.util.StringUtils;

import java.util.Map;

public class BaseFileUploader {

    ApiClient getApiClient(String uploadType) {
        ConfigService configService = SpringContextHolder.getBean(ConfigService.class);
        Map<String, Object> config = configService.getConfigs();
        String storageType = null;
        if (null == config || StringUtils.isEmpty((storageType = (String) config.get(ConfigKeyEnum.STORAGE_TYPE.getKey())))) {
            throw new ZhydException("[文件服务]当前系统暂未配置文件服务相关的内容！");
        }

        ApiClient res = null;
        switch (storageType) {
            case "local":
                String localFileUrl = (String) config.get(ConfigKeyEnum.LOCAL_FILE_URL.getKey()),
                        localFilePath = (String) config.get(ConfigKeyEnum.LOCAL_FILE_PATH.getKey());
                res = (ApiClient) new LocalApiClient().init(localFileUrl, localFilePath, uploadType);
                break;
            case "qiniu":
                String accessKey = (String) config.get(ConfigKeyEnum.QINIU_ACCESS_KEY.getKey()),
                        secretKey = (String) config.get(ConfigKeyEnum.QINIU_SECRET_KEY.getKey()),
                        qiniuBucketName = (String) config.get(ConfigKeyEnum.QINIU_BUCKET_NAME.getKey()),
                        baseUrl = (String) config.get(ConfigKeyEnum.QINIU_BASE_PATH.getKey());
                res = (ApiClient) new QiniuApiClient().init(accessKey, secretKey, qiniuBucketName, baseUrl, uploadType);
                break;
            case "aliyun":
                String endpoint = (String) config.get(ConfigKeyEnum.ALIYUN_ENDPOINT.getKey()),
                        accessKeyId = (String) config.get(ConfigKeyEnum.ALIYUN_ACCESS_KEY.getKey()),
                        accessKeySecret = (String) config.get(ConfigKeyEnum.ALIYUN_ACCESS_KEY_SECRET.getKey()),
                        url = (String) config.get(ConfigKeyEnum.ALIYUN_FILE_URL.getKey()),
                        aliYunBucketName = (String) config.get(ConfigKeyEnum.ALIYUN_BUCKET_NAME.getKey());
                res = new AliyunOssApiClient().init(endpoint, accessKeyId, accessKeySecret, url, aliYunBucketName, uploadType);
                break;
            case "youpaiyun":
                break;
            default:
                break;
        }
        if (null == res) {
            throw new GlobalFileException("[文件服务]当前系统暂未配置文件服务相关的内容！");
        }
        return res;
    }

    VirtualFile saveFile(VirtualFile virtualFile, boolean save, String uploadType) {
        if (save) {
            BizFileService fileService = SpringContextHolder.getBean(BizFileService.class);
            try {
                ConfigService configService = SpringContextHolder.getBean(ConfigService.class);
                Map<String, Object> config = configService.getConfigs();
                String storageType = (String) config.get(ConfigKeyEnum.STORAGE_TYPE.getKey());

                BlFile fileInfo = BeanConvertUtil.doConvert(virtualFile, BlFile.class);
                User sessionUser = SessionUtil.getUser();
                fileInfo.setUserId(null == sessionUser ? null : sessionUser.getId());
                fileInfo.setUploadType(uploadType);
                fileInfo.setStorageType(storageType);
                fileService.insert(new File(fileInfo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return virtualFile;
    }

    boolean removeFile(ApiClient apiClient, String filePath, String uploadType) {
        BizFileService fileService = SpringContextHolder.getBean(BizFileService.class);
        File file = fileService.selectFileByPathAndUploadType(filePath, uploadType);
        String fileKey = filePath;
        if (null != file) {
            fileKey = file.getFilePath();
        }
        return apiClient.removeFile(fileKey);
    }

}
