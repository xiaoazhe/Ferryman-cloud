package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
public interface BlFileMapper extends BaseMapper<BlFile> {
    int updateBatch(List <BlFile> list);

    int batchInsert(@Param("list") List <BlFile> list);

    int insertOrUpdate(BlFile record);

    int insertOrUpdateSelective(BlFile record);
}