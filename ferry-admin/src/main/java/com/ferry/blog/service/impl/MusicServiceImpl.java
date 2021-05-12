package com.ferry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.blog.entity.BlBlog;
import com.ferry.blog.entity.BlFile;
import com.ferry.blog.entity.BlMusic;
import com.ferry.blog.mapper.BlFileMapper;
import com.ferry.blog.mapper.BlMusicMapper;
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

//    @Override
//    public Page<BlMusic> getByPage(Page<BlMusic> page) {
//        // 查询数据
//        List <BlMusic> musicList = musicMapper.getByPage(page);
//        page.setList(musicList);
//        // 查询总数
//        int totalCount = musicMapper.getCountByPage(page);
//        page.setTotalCount(totalCount);
//        return page;
//    }

    @Override
    public PageResult getByPage(PageRequest pageRequest) {
        Page <BlMusic> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<BlMusic> queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isBlank(pageRequest.getName()),"name", pageRequest.getName());
        queryWrapper.like(!StringUtils.isBlank(pageRequest.getName()),"artist", pageRequest.getName());
        queryWrapper.eq(pageRequest.getEnabled() > -1,"enable", pageRequest.getEnabled());
        Page<BlMusic> typePage = musicMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult(typePage);
        return pageResult;
    }
    @Override
    public List<BlMusic> getList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("id", 0);
        return musicMapper.selectList(queryWrapper);
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        if (StringUtils.isBlank(str)) {
            return false;
        }
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
