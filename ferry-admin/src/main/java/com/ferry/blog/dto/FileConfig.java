package com.ferry.blog.dto;

import lombok.Data;

@Data
public class FileConfig {

    /**
     * 七牛云域名 http://basePath + /
     * 阿里云 http://oss-cn-beijing.aliyuncs.com
     */
    private String basePath;

    /**
     * 阿里云 http://"bucketName".oss-cn-beijing.aliyuncs.com/
     */
    private String fileUrl;

    /**
     * AccessKey
     */
    private String accessKey;

    /**
     * Secret Key
     */
    private String secretKey;

    /**
     * bucketName
     */
    private String bucketName;

    /**
     * 存储类型
     */
    private String storageType;
}
