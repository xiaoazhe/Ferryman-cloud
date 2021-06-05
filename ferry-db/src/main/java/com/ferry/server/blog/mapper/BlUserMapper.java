package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/6/5
 */
public interface BlUserMapper extends BaseMapper<BlUser> {
    int updateBatch(List<BlUser> list);

    int batchInsert(@Param("list") List<BlUser> list);

    int insertOrUpdate(BlUser record);

    int insertOrUpdateSelective(BlUser record);
}