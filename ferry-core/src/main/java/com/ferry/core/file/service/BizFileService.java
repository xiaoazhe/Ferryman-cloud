package com.ferry.core.file.service;


import com.ferry.core.file.entity.File;
import com.ferry.core.file.entity.bean.FileConditionVO;
import com.ferry.core.file.object.AbstractService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
public interface BizFileService extends AbstractService <File, Long> {

    PageInfo <File> findPageBreakByCondition(FileConditionVO vo);

    File selectFileByPathAndUploadType(String filePath, String uploadType);

    void remove(Long[] ids);

    int upload(MultipartFile[] file);
}
