package com.ferry.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.blog.entity.BlFriendLink;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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