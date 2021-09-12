package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysNotifyRecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
public interface SysNotifyRecordMapper extends BaseMapper<SysNotifyRecord> {
    int updateBatch(List<SysNotifyRecord> list);

    int batchInsert(@Param("list") List<SysNotifyRecord> list);

    int insertOrUpdate(SysNotifyRecord record);

    int insertOrUpdateSelective(SysNotifyRecord record);
}