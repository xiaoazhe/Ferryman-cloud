package com.ferry.core.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ferry.core.file.FileUploader;
import com.ferry.core.file.GlobalFileUploader;
import com.ferry.core.file.emums.FileUploadType;
import com.ferry.core.file.entity.File;
import com.ferry.core.file.entity.bean.BlFile;
import com.ferry.core.file.entity.bean.FileConditionVO;
import com.ferry.core.file.exception.exception.GlobalFileException;
import com.ferry.core.file.mapper.BizFileMapper;
import com.ferry.core.file.service.BizFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
@Service
public class BizFileServiceImpl implements BizFileService {

    @Autowired
    private BizFileMapper shopFileMapper;

    @Override
    public PageInfo <File> findPageBreakByCondition(FileConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BlFile> list = shopFileMapper.findPageBreakByCondition(vo);
        List<File> boList = getFiles(list);
        if (boList == null) return null;
        PageInfo bean = new PageInfo <BlFile>(list);
        bean.setList(boList);
        return bean;
    }

    private List<File> getFiles(List<BlFile> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<File> boList = new ArrayList<>();
        for (BlFile blFile : list) {
            boList.add(new File(blFile));
        }
        return boList;
    }

    @Override
    public File selectFileByPathAndUploadType(String filePath, String uploadType) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        BlFile file = new BlFile();
        file.setFilePath(filePath);
        if (StringUtils.isEmpty(uploadType)) {
            file.setUploadType(uploadType);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("file_path", filePath);
        queryWrapper.eq("upload_type", uploadType);
        List<BlFile> fileList = shopFileMapper.selectList(queryWrapper);
        return CollectionUtils.isEmpty(fileList) ? null : new File(fileList.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long[] ids) {
        for (Long id : ids) {
            File oldFile = this.getByPrimaryKey(id);
            this.removeByPrimaryKey(id);
            try {
                FileUploader uploader = new GlobalFileUploader();
                uploader.delete(oldFile.getFilePath(), oldFile.getUploadType());
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int upload(MultipartFile[] file) {
        if (null == file || file.length == 0) {
            throw new GlobalFileException("请至少选择一张图片！");
        }
        for (MultipartFile multipartFile : file) {
            FileUploader uploader = new GlobalFileUploader();
            uploader.upload(multipartFile, FileUploadType.COMMON.getPath(), true);
        }
        return file.length;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public File insert(File entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        shopFileMapper.insert(entity.getFile());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "Invalid parameter");
        return shopFileMapper.deleteById(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(File entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setUpdateTime(new Date());
        return shopFileMapper.updateById(entity.getFile()) > 0;
    }

    @Override
    public File getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "Invalid parameter");
        BlFile entity = shopFileMapper.selectById(primaryKey);
        return new File(entity);
    }

    @Override
    public List<File> listAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<BlFile> list = shopFileMapper.selectList(queryWrapper);
        return getFiles(list);
    }
}
