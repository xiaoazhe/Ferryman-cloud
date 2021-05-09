package com.ferry.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.blog.entity.BlFile;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/8
 */
public interface BlFileMapper extends BaseMapper<BlFile> {
    int updateBatch(List<BlFile> list);

    int batchInsert(@Param("list") List<BlFile> list);

    int insertOrUpdate(BlFile record);

    int insertOrUpdateSelective(BlFile record);
}