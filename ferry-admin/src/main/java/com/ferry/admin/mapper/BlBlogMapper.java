package com.ferry.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.admin.entity.BlBlog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
public interface BlBlogMapper extends BaseMapper<BlBlog> {
    int updateBatch(List<BlBlog> list);

    int batchInsert(@Param("list") List<BlBlog> list);

    int insertOrUpdate(BlBlog record);

    int insertOrUpdateSelective(BlBlog record);
}