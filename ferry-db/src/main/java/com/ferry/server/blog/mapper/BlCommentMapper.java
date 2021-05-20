package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ferry.server.blog.entity.BlComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/11
 */
public interface BlCommentMapper extends BaseMapper<BlComment> {
    int updateBatch(List <BlComment> list);

    int batchInsert(@Param("list") List <BlComment> list);

    int insertOrUpdate(BlComment record);

    int insertOrUpdateSelective(BlComment record);

    Page<BlComment> selectPageNew(Page <BlComment> page, @Param("ew") QueryWrapper <BlComment> queryWrapper);

    List<BlComment> selectCommentList(@Param("ew") QueryWrapper <BlComment> queryWrapper);
}