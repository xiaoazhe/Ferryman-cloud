package com.ferry.blog.controller;


import com.ferry.blog.entity.BlMusic;
import com.ferry.blog.service.MusicService;
import com.ferry.common.utils.StringUtils;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: 码仔
 * @Date: 2020/2/9 20:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @PostMapping(value="/getByPage")
    public Result findPage(@RequestBody PageRequest page) {

        return Result.ok(musicService.getByPage(page));
    }

    /**
     * 保存
     *
     * @param music
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:add')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody BlMusic music) {
        if (music.getId() != null) {
            music.setUpdateTime(new Date());
            return Result.ok(musicService.updateById(music));
        }
        return Result.ok(musicService.save(music));
    }

    /**
     * 更新
     *
     * @param music
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:edit')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody BlMusic music) {
        musicService.updateById(music);
        return Result.ok("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result get(@PathVariable String id) {
        BlMusic music = musicService.getById(id);
        return Result.ok(music);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:delete')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id) {
        musicService.deleteById(id);
        return Result.ok("删除成功！");
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable Integer id) {
        musicService.enableById(id);
        return Result.ok("启用成功");
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PreAuthorize("hasAuthority('sys:music:view')")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable Integer id) {
        musicService.disableById(id);
        return Result.ok("弃用成功");
    }


    /**
     * 前台查询
     *
     * @return
     */
    @ApiOperation(value = "前台查询")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result getList() {
        List<BlMusic> musicList = musicService.getList();
        return Result.ok(musicList);
    }

}
