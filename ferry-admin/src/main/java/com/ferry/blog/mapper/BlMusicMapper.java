package com.ferry.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.blog.entity.BlMusic;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/10
 */
public interface BlMusicMapper extends BaseMapper <BlMusic> {
    int updateBatch(List <BlMusic> list);

    int batchInsert(@Param("list") List <BlMusic> list);

    int insertOrUpdate(BlMusic record);

    int insertOrUpdateSelective(BlMusic record);
}