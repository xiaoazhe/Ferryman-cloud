package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlReply;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface BlReplyMapper extends BaseMapper<BlReply> {
    int updateBatch(List<BlReply> list);

    int batchInsert(@Param("list") List<BlReply> list);

    int insertOrUpdate(BlReply record);

    int insertOrUpdateSelective(BlReply record);
}