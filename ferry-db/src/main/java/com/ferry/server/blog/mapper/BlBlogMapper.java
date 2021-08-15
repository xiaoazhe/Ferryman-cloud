package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlBlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
public interface BlBlogMapper extends BaseMapper<BlBlog> {
    int updateBatch(List <BlBlog> list);

    int batchInsert(@Param("list") List <BlBlog> list);

    int insertOrUpdate(BlBlog record);

    int insertOrUpdateSelective(BlBlog record);

    Integer getClickByCreantName(@Param("name") String name);

    Integer getCollectByCreantName(@Param("name") String name);

    Integer getMaterialByCreantName(@Param("name") String name);
}