package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlUserLabel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface BlUserLabelMapper extends BaseMapper<BlUserLabel> {
    int updateBatch(List<BlUserLabel> list);

    int batchInsert(@Param("list") List<BlUserLabel> list);

    int insertOrUpdate(BlUserLabel record);

    int insertOrUpdateSelective(BlUserLabel record);
}