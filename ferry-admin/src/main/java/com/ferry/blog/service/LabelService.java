package com.ferry.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;
import com.ferry.server.blog.entity.BlLabel;


/**
 * @Author: 摆渡人
 * @Date: 2021/6/9
 */
public interface LabelService extends IService <BlLabel> {

    PageResult selectAllByUser(PageRequest pageRequest);

    String saveLabel(BlLabel label);

    String updateLabelState(BlLabel label);
}
