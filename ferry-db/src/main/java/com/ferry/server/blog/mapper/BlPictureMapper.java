package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlPicture;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/21
 */
public interface BlPictureMapper extends BaseMapper<BlPicture> {
    int updateBatch(List<BlPicture> list);

    int batchInsert(@Param("list") List<BlPicture> list);

    int insertOrUpdate(BlPicture record);

    int insertOrUpdateSelective(BlPicture record);
}