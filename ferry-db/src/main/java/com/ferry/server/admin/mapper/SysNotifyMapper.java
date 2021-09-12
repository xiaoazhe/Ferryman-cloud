package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysNotify;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
public interface SysNotifyMapper extends BaseMapper<SysNotify> {
    int updateBatch(List<SysNotify> list);

    int batchInsert(@Param("list") List<SysNotify> list);

    int insertOrUpdate(SysNotify record);

    int insertOrUpdateSelective(SysNotify record);
}