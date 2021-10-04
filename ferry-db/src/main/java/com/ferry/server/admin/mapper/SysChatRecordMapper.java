package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysChatRecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Author: 摆渡人
 * @Date: 2021/10/3
 */
public interface SysChatRecordMapper extends BaseMapper<SysChatRecord> {
    int updateBatch(List<SysChatRecord> list);

    int batchInsert(@Param("list") List<SysChatRecord> list);

    int insertOrUpdate(SysChatRecord record);

    int insertOrUpdateSelective(SysChatRecord record);
}