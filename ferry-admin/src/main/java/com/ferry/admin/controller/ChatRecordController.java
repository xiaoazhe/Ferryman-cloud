package com.ferry.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ferry.admin.service.ChatRecordService;
import com.ferry.common.utils.IdWorker;
import com.ferry.core.http.Result;
import com.ferry.server.admin.entity.SysChatRecord;
import com.ferry.server.admin.vo.ImChatRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: 摆渡人
 * @Date: 2021/10/5
 */
@Api(tags = "聊天记录")
@RestController
@RequestMapping("chatRecord")
public class ChatRecordController {
    private final Logger logger = LoggerFactory.getLogger(ChatRecordController.class);

    @Resource
    public ChatRecordService chatRecordService;

    @Resource
    private IdWorker idWorker;

    @ApiOperation("更新消息为已读")
    @PostMapping(value = "/updRead")
    public Result imChatRecordUpdate(@RequestBody ImChatRecordVo imChatRecordVo) {
        try {
            chatRecordService.imChatRecordUpdate(imChatRecordVo);
            return Result.ok();
        } catch (Exception e) {
            throw new RuntimeException("更新消息为已读异常", e);
        }
    }

    @ApiOperation("查询出未读消息记录")
    @GetMapping(value = "/unReadCount")
    public Result getUnReadImChatRecordCount(ImChatRecordVo imChatRecordVo) {
        try {
            Map <String, Object> result = chatRecordService.getUnReadImChatRecordCount(imChatRecordVo);
            return Result.ok(result);
        } catch (Exception e) {
            throw new RuntimeException("查询出未读消息记录", e);
        }
    }

    @ApiOperation("无分页查询聊天列表数据")
    @PostMapping(value = "/list")
    public Result getImChatRecordList(@RequestBody ImChatRecordVo imChatRecordVo) {
        try {
            List <ImChatRecordVo> result = chatRecordService.selectRecordList(imChatRecordVo);
            return Result.ok(result);
        } catch (Exception e) {
            throw new RuntimeException("无分页查询数据异常", e);
        }
    }

    @ApiOperation("无分页查询聊天记录详情数据")
    @GetMapping(value = "/info/list")
    public Result getImChatRecordInfoList(ImChatRecordVo imChatRecordVo) {
        try {
            List<ImChatRecordVo> result = chatRecordService.selectRecordInfoList(imChatRecordVo);
            return Result.ok(result);
        } catch (Exception e) {
            throw new RuntimeException("无分页查询数据异常", e);
        }
    }

    @ApiOperation("分页查询数据")
    @GetMapping("/list/page")
    public Result getImChatRecordListPage(SysChatRecord chatRecord) {
        try {
            Page <SysChatRecord> page = new Page<>();
            QueryWrapper <SysChatRecord> queryWrapper = new QueryWrapper<>(chatRecord);
            IPage <SysChatRecord> result = chatRecordService.page(page, queryWrapper);
            return Result.ok(result);
        } catch (Exception e) {
            throw new RuntimeException("分页查询数据异常", e);
        }
    }

    @ApiOperation("新增数据")
    @PostMapping(value = "/save")
    public Result imChatRecordSave(SysChatRecord imChatRecord) {
        try {
            imChatRecord.setId(idWorker.nextId() + "");
            chatRecordService.save(imChatRecord);
            return Result.ok(imChatRecord);
        } catch (Exception e) {
            throw new RuntimeException("新增数据异常", e);
        }
    }

    @ApiOperation("删除聊天记录")
    @GetMapping(value = "/del")
    public Result imChatRecordDelete(ImChatRecordVo imChatRecordVo) {
        try {
            int count = chatRecordService.imChatRecordDelete(imChatRecordVo) ? 1 : 0;
            return Result.ok(count);
        } catch (Exception e) {
            throw new RuntimeException("根据id删除对象异常", e);
        }
    }

    @ApiOperation("批量删除对象")
    @DeleteMapping(value = "del/batch")
    public Result deleteBatchIds(@RequestParam List<Long> ids) {
        try {
            int count = chatRecordService.removeByIds(ids) ? 1 : 0;
            return Result.ok(count);
        } catch (Exception e) {
            throw new RuntimeException("批量删除对象异常", e);
        }
    }
}
