package com.ferry.core.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.core.file.entity.Config;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Map;

@MapperScan
public interface ConfigMapper extends BaseMapper <Config> {
    Map<String, Object> getSiteInfo();
}
