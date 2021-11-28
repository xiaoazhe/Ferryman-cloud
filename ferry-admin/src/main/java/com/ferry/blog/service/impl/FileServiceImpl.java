package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.blog.dto.FileConfig;
import com.ferry.common.enums.StateEnums;
import com.ferry.core.file.entity.Config;
import com.ferry.core.file.holder.SpringContextHolder;
import com.ferry.core.file.mapper.ConfigMapper;
import com.ferry.core.file.service.ConfigService;
import com.ferry.server.blog.entity.BlFile;
import com.ferry.server.blog.mapper.BlFileMapper;
import com.ferry.blog.service.FileService;
import com.ferry.core.file.FileUploader;
import com.ferry.core.file.GlobalFileUploader;
import com.ferry.core.file.emums.FileUploadType;
import com.ferry.core.file.entity.VirtualFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
@Service
public class FileServiceImpl extends ServiceImpl <BlFileMapper, BlFile> implements FileService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public String uploadFile(MultipartFile image) {
        String imgUrl = null;
        try {
            imgUrl = uploader(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;
    }

    @Override
    public String fileConfig(FileConfig fileConfig) {
        if ("aliyun".equals(fileConfig.getStorageType())) {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            ArrayList list = new ArrayList<String>();
            list.add("aliyunBucketName");
            list.add("aliyunEndpoint");
            list.add("aliyunFileUrl");
            list.add("aliyunAccessKey");
            list.add("aliyunAccessKeySecret");
            list.add("storageType");
            queryWrapper.in("config_key", list);
            List<Config> configList = configMapper.selectList(queryWrapper);
            for (Config config : configList) {
                switch (config.getConfigKey()) {
                    case "aliyunBucketName":
                        config.setConfigValue(fileConfig.getBucketName());
                        configMapper.updateById(config);
                        break;
                    case "aliyunEndpoint":
                        config.setConfigValue(fileConfig.getBasePath());
                        configMapper.updateById(config);
                        break;
                    case "aliyunFileUrl":
                        config.setConfigValue(fileConfig.getFileUrl());
                        configMapper.updateById(config);
                        break;
                    case "aliyunAccessKey":
                        config.setConfigValue(fileConfig.getAccessKey());
                        configMapper.updateById(config);
                        break;
                    case "aliyunAccessKeySecret":
                        config.setConfigValue(fileConfig.getSecretKey());
                        configMapper.updateById(config);
                        break;
                    case "storageType":
                        config.setConfigValue("aliyun");
                        configMapper.updateById(config);
                        break;
                    default:
                        break;
                }
            }
        } else if ("qiniu".equals(fileConfig.getStorageType())) {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            ArrayList list = new ArrayList<String>();
            list.add("qiniuBasePath");
            list.add("qiniuAccessKey");
            list.add("qiniuSecretKey");
            list.add("qiniuBucketName");
            list.add("storageType");
            queryWrapper.in("config_key", list);
            List<Config> configList = configMapper.selectList(queryWrapper);
            for (Config config : configList) {
                switch (config.getConfigKey()) {
                    case "qiniuBasePath":
                        config.setConfigValue(fileConfig.getBasePath());
                        configMapper.updateById(config);
                        break;
                    case "qiniuAccessKey":
                        config.setConfigValue(fileConfig.getAccessKey());
                        configMapper.updateById(config);
                        break;
                    case "qiniuSecretKey":
                        config.setConfigValue(fileConfig.getSecretKey());
                        configMapper.updateById(config);
                        break;
                    case "qiniuBucketName":
                        config.setConfigValue(fileConfig.getBucketName());
                        configMapper.updateById(config);
                        break;
                    case "storageType":
                        config.setConfigValue("qiniu");
                        configMapper.updateById(config);
                        break;
                    default:
                        break;
                }
            }
        }
        return StateEnums.REQUEST_SUCCESS.getMsg();
    }

    @Override
    public List<FileConfig> getFileConfig() {
        ConfigService configService = SpringContextHolder.getBean(ConfigService.class);
        Map<String, Object> config = configService.getConfigs();
        FileConfig fileConfig = new FileConfig();
        fileConfig.setBucketName((String) config.get("aliyunBucketName"));
        fileConfig.setBasePath((String) config.get("aliyunEndpoint"));
        fileConfig.setFileUrl((String) config.get("aliyunFileUrl"));
        fileConfig.setAccessKey((String) config.get("aliyunAccessKey"));
        fileConfig.setSecretKey((String) config.get("aliyunAccessKeySecret"));
        fileConfig.setStorageType("aliyun");

        FileConfig qiniuConfig = new FileConfig();
        qiniuConfig.setBasePath((String) config.get("qiniuBasePath"));
        qiniuConfig.setAccessKey((String) config.get("qiniuAccessKey"));
        qiniuConfig.setSecretKey((String) config.get("qiniuSecretKey"));
        qiniuConfig.setBucketName((String) config.get("qiniuBucketName"));
        qiniuConfig.setStorageType("qiniu");
        ArrayList<FileConfig> fileConfigs = new ArrayList<>();
        fileConfigs.add(fileConfig);
        fileConfigs.add(qiniuConfig);
        return fileConfigs;
    }

    public String uploader(MultipartFile multipartFile) {
        FileUploader uploader = new GlobalFileUploader();
        VirtualFile virtualFile = uploader.upload(multipartFile, FileUploadType.COMMON.getPath(), true);
        return virtualFile.getFullFilePath();
    }
}
