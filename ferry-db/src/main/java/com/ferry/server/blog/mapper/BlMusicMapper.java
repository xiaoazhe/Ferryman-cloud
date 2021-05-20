package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlMusic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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