package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.blog.entity.BlType;
import com.ferry.blog.mapper.BlTypeMapper;
import com.ferry.blog.service.TypeService;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/7
 */
@Service
public class TypeServiceImpl extends ServiceImpl <BlTypeMapper, BlType> implements TypeService {

    @Autowired
    private BlTypeMapper blTypeMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Page <BlType> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        String label = pageRequest.getParamValue("name");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isBlank(label),"name",label);
        queryWrapper.ne("available",0);
        Page<BlType> typePage = blTypeMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(typePage);
        return pageResult;
    }


    @Override
    public boolean removeTypes(List <BlType> types) {
        for (BlType type : types) {
            blTypeMapper.deleteById(type.getId());
        }
        return true;
    }

    @Override
    public boolean saveType(BlType type) {
        if(type.getId() != null) {
            BlType oldType =  blTypeMapper.selectById(type.getId());
            if (oldType != null) {
                oldType.setAvailable(type.getAvailable());
                oldType.setUpdateTime(new Date());
                oldType.setDescription(type.getDescription());
                oldType.setName(type.getName());
                oldType.setSort(type.getSort());
                blTypeMapper.updateById(oldType);
            }
        } else {
            type.setCreateTime(new Date());
            blTypeMapper.insert(type);
        }
        return true;
    }

    @Override
    public List <BlType> findAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("id", 0);
        List <BlType> typeList= blTypeMapper.selectList(queryWrapper);
        return typeList;
    }

    @Override
    public BlType findById(Integer id) {
        BlType typeName = blTypeMapper.selectById(id);
        return typeName;
    }
}
