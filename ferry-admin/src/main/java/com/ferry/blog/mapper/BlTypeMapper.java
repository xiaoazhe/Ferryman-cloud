package com.ferry.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.blog.entity.BlType;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
public interface BlTypeMapper extends BaseMapper <BlType> {
    int updateBatch(List <BlType> list);

    int batchInsert(@Param("list") List <BlType> list);

    int insertOrUpdate(BlType record);

    int insertOrUpdateSelective(BlType record);
}