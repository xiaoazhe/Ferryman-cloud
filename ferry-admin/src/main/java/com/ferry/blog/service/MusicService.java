package com.ferry.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.blog.entity.BlMusic;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/10
 */
public interface MusicService extends IService <BlMusic> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlMusic getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 弃用
     * @param id
     */
    void disableById(Integer id);

    /**
     * 分页
     * @param page
     * @return
     */
    PageResult getByPage(PageRequest page);

    /**
     * 前台查询
     * @return
     */
    List <BlMusic> getList();
}