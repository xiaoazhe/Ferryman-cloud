package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.util.SecurityUtils;
import com.ferry.blog.service.MaterialService;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlMaterial;
import com.ferry.server.blog.mapper.BlMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/27
 */
@Service
public class MaterialServiceImpl extends ServiceImpl <BlMaterialMapper, BlMaterial> implements MaterialService {

    @Autowired
    private BlMaterialMapper materialMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Page <BlMaterial> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        String label = pageRequest.getName();
        QueryWrapper <BlMaterial> queryWrapper = new QueryWrapper <>();
        queryWrapper.like(!StringUtils.isBlank(label), BlMaterial.COL_TITLE, label);
        queryWrapper.orderByDesc(BlMaterial.COL_SORT);
        Page<BlMaterial> problemPage = materialMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(problemPage);
        return pageResult;
    }

    @Override
    public String saveMaterial(BlMaterial material) {
        material.setStatus(1);
        material.setCreateTime(new Date());
        material.setCreateBy(SecurityUtils.getUsername());
        materialMapper.insert(material);
        return StateEnums.REQUEST_SUCCESS.getMsg();
    }

    @Override
    public String updateMaterial(BlMaterial material) {
        materialMapper.updateById(material);
        material.setUpdateTime(new Date());
        material.setLastUpdateBy(SecurityUtils.getUsername());
        return StateEnums.REQUEST_SUCCESS.getMsg();
    }

    @Override
    public BlMaterial getById(Integer id) {
        return materialMapper.selectById(id);
    }

    @Override
    public String audit(Integer id) {
        BlMaterial material = materialMapper.selectById(id);
        if (material.getStatus() == 0) {
            material.setStatus(1);
        } else {
            material.setStatus(0);
        }
        materialMapper.updateById(material);
        return StateEnums.REQUEST_SUCCESS.getMsg();
    }
}
