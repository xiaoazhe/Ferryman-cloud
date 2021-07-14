package com.ferry.blog.controller;

import com.ferry.blog.service.MaterialService;
import com.ferry.common.enums.StateEnums;
import com.ferry.core.http.Result;
import com.ferry.core.page.PageRequest;
import com.ferry.server.blog.entity.BlMaterial;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/27
 */

@Api(tags = "资料链接")
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @ApiOperation(value = "分页列表")
    @PostMapping(value = "/findPage")
    @PreAuthorize("hasAuthority('sys:material:view')")
    public Result findPage(@RequestBody PageRequest pageRequest){
        return Result.ok(materialService.findPage(pageRequest));
    }

    @ApiOperation(value = "添加")
    @PostMapping(value = "/save")
    @PreAuthorize("hasAuthority('sys:material:add')")
    public Result save(@RequestBody BlMaterial material){
        if (material.getId() != 0) {
            return Result.ok(materialService.updateMaterial(material));
        }
        return Result.ok(materialService.saveMaterial(material));
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/update")
    @PreAuthorize("hasAuthority('sys:material:edit')")
    public Result update(@RequestBody BlMaterial material){
        if (material.getId() == null) {
            throw new RuntimeException(StateEnums.REQUEST_ERROR.getMsg());
        }
        return Result.ok(materialService.updateMaterial(material));
    }

    @ApiOperation(value = "id查询")
    @GetMapping(value = "/getById/{id}")
    @PreAuthorize("hasAuthority('sys:material:view')")
    public Result getById(@PathVariable Integer id){
        if (id == null) {
            throw new RuntimeException(StateEnums.REQUEST_ERROR.getMsg());
        }
        return Result.ok(materialService.getById(id));
    }

    @ApiOperation(value = "删除")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('sys:material:delete')")
    public Result delete(@PathVariable Integer id){
        if (id == null) {
            throw new RuntimeException(StateEnums.REQUEST_ERROR.getMsg());
        }
        materialService.removeById(id);
        return Result.ok(StateEnums.REQUEST_SUCCESS.getMsg());
    }

    @ApiOperation(value = "审核")
    @GetMapping(value = "/audit/{id}")
    @PreAuthorize("hasAuthority('sys:material:view')")
    public Result audit(@PathVariable Integer id){
        if (id == null) {
            throw new RuntimeException(StateEnums.REQUEST_ERROR.getMsg());
        }
        return Result.ok(materialService.audit(id));
    }
}
