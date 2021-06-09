package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlProblem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface BlProblemMapper extends BaseMapper<BlProblem> {
    int updateBatch(List<BlProblem> list);

    int batchInsert(@Param("list") List<BlProblem> list);

    int insertOrUpdate(BlProblem record);

    int insertOrUpdateSelective(BlProblem record);
}