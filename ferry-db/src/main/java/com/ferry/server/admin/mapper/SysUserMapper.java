package com.ferry.server.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.server.admin.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findByName(@Param(value = "name") String name);

    List<SysUser> findPageByNameAndEmail(@Param(value = "name") String name, @Param(value = "email") String email);

    List<SysUser> findPageByName(@Param(value = "name") String name);

    List<SysUser> findPage();

    SysUser findByMobile(@Param(value = "mobile") String mobile);
}