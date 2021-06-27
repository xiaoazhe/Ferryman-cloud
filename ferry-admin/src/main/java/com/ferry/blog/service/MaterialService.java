package com.ferry.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlMaterial;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/27
 */
public interface MaterialService extends IService <BlMaterial> {

    PageResult findPage(PageRequest pageRequest);

    String saveMaterial(BlMaterial material);

    String updateMaterial(BlMaterial material);

    BlMaterial getById(Integer id);

    String audit(Integer id);
}
