package com.ferry.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.service.SysNotifyRecordService;
import com.ferry.server.admin.entity.SysNotifyRecord;
import com.ferry.server.admin.mapper.SysNotifyRecordMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 摆渡人
 * @Date: 2021/9/12
 */
@Service
public class SysNotifyRecordServiceImpl extends ServiceImpl <SysNotifyRecordMapper, SysNotifyRecord> implements SysNotifyRecordService {
}
