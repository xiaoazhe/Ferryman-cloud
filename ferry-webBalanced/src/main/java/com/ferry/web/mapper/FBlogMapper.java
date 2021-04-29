package com.ferry.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.web.domain.FBlog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/4/29
 */
public interface FBlogMapper extends BaseMapper<FBlog> {
    int updateBatch(List<FBlog> list);

    int batchInsert(@Param("list") List<FBlog> list);

    int insertOrUpdate(FBlog record);

    int insertOrUpdateSelective(FBlog record);
}