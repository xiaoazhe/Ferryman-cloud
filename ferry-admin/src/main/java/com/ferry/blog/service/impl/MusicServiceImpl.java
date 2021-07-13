package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.server.blog.entity.BlBlog;
import com.ferry.server.blog.entity.BlFile;
import com.ferry.server.blog.entity.BlMusic;
import com.ferry.server.blog.mapper.BlFileMapper;
import com.ferry.server.blog.mapper.BlMusicMapper;
import com.ferry.blog.service.FileService;
import com.ferry.blog.service.MusicService;
import com.ferry.common.enums.StateEnums;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/10
 */

@Service
@Slf4j
public class MusicServiceImpl extends ServiceImpl <BlMusicMapper, BlMusic> implements MusicService {

    @Autowired
    private BlMusicMapper musicMapper;


    @Override
    public BlMusic getById(Integer id) {
        return musicMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        musicMapper.deleteById(id);
    }

    @Override
    public void enableById(Integer id) {
        BlMusic music = musicMapper.selectById(id);
        music.setEnable(StateEnums.ENABLED.getCode());
        musicMapper.updateById(music);
    }

    @Override
    public void disableById(Integer id) {
        BlMusic music = musicMapper.selectById(id);
        music.setEnable(StateEnums.NOT_ENABLE.getCode());
        musicMapper.updateById(music);
    }

    @Override
    public PageResult getByPage(PageRequest pageRequest) {
        Page <BlMusic> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<BlMusic> queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isBlank(pageRequest.getName()), BlMusic.COL_NAME, pageRequest.getName());
        queryWrapper.like(!StringUtils.isBlank(pageRequest.getName()), BlMusic.COL_ARTIST, pageRequest.getName());
        queryWrapper.eq(pageRequest.getEnabled() > 0, BlMusic.COL_ENABLE, pageRequest.getEnabled());
        Page<BlMusic> typePage = musicMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(typePage);
        return pageResult;
    }
    @Override
    public List<BlMusic> getList() {
        QueryWrapper<BlMusic> queryWrapper = new QueryWrapper();
        queryWrapper.ne(BlMusic.COL_ID, 0);
        return musicMapper.selectList(queryWrapper);
    }
}
