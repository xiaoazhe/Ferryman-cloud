package com.ferry.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.admin.entity.SysUser;
import org.apache.ibatis.annotations.Param;


public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findByName(@Param(value="name") String name);

}