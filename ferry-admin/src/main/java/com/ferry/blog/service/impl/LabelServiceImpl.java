package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.admin.util.SecurityUtils;
import com.ferry.blog.service.LabelService;
import com.ferry.common.enums.StateEnums;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlLabel;
import com.ferry.server.blog.mapper.BlLabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Security;
import java.util.Date;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
@Service
public class LabelServiceImpl extends ServiceImpl <BlLabelMapper, BlLabel> implements LabelService {

    @Resource
    private BlLabelMapper labelMapper;

    @Override
    public PageResult selectAllByUser(PageRequest pageRequest) {
        Page <BlLabel> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper <BlLabel> queryWrapper = new QueryWrapper();
        queryWrapper.ne(BlLabel.COL_STATE, 0);
        Page <BlLabel> labelPage = labelMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(labelPage);
        return pageResult;
    }

    @Override
    public String saveLabel(BlLabel label) {
        if (label.getId() == null) {
            label.setCount(0);
            label.setState("1");
            label.setFans("0");
            label.setCreateBy(SecurityUtils.getUsername());
            label.setCreateTime(new Date());
            labelMapper.insert(label);
            return StateEnums.REQUEST_SUCCESS.getMsg();
        } else {
            BlLabel blLabel = labelMapper.selectById(label.getId());
            blLabel.setLabelname(label.getLabelname());
            blLabel.setLastUpdateBy(SecurityUtils.getUsername());
            blLabel.setState(label.getState());
            blLabel.setUpdateTime(new Date());
            labelMapper.updateById(blLabel);
            return StateEnums.REQUEST_SUCCESS.getMsg();
        }
    }
}
