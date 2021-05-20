package com.ferry.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.server.blog.entity.BlFriendLink;
import com.ferry.core.page.PageRequest;
import com.ferry.core.page.PageResult;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/5/12
 */
public interface FriendService extends IService <BlFriendLink> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlFriendLink getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    String deleteById(Integer id);

    /**
     * 启用
     * @param id
     */
    String enableById(Integer id);

    /**
     * 弃用
     * @param id
     */
    String disableById(Integer id);

    /**
     * 分页
     * @param page
     * @return
     */
    PageResult getByPage(PageRequest page);

    /**
     * 批量删除
     * @param friendLinks
     * @return
     */
    String removeTypes(List <BlFriendLink> friendLinks);
}
