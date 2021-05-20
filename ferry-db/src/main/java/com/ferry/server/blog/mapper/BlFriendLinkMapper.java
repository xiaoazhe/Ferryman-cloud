package com.ferry.server.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.blog.entity.BlFriendLink;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/12
 */
public interface BlFriendLinkMapper extends BaseMapper <BlFriendLink> {
    int updateBatch(List <BlFriendLink> list);

    int batchInsert(@Param("list") List <BlFriendLink> list);

    int insertOrUpdate(BlFriendLink record);

    int insertOrUpdateSelective(BlFriendLink record);
}