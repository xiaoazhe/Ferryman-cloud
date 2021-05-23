package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlPictureSort;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/23
 */
public interface BlPictureSortMapper extends BaseMapper<BlPictureSort> {
    int updateBatch(List<BlPictureSort> list);

    int batchInsert(@Param("list") List<BlPictureSort> list);

    int insertOrUpdate(BlPictureSort record);

    int insertOrUpdateSelective(BlPictureSort record);
}