package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlCollect;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/6/12
 */
public interface BlCollectMapper extends BaseMapper<BlCollect> {
    int updateBatch(List<BlCollect> list);

    int batchInsert(@Param("list") List<BlCollect> list);

    int insertOrUpdate(BlCollect record);

    int insertOrUpdateSelective(BlCollect record);
}