package com.ferry.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：通用树结构
 * <p>
 * 作者：Ostrich Hu
 * 时间：2019/10/9 11:11
 */
@Data
public class MenuTree<T> {

    private String path;

    private String name;

    private String icon;

    private String id;

    private String pId;
    /**
     * 排序字段
     */
    private String order;

    public MenuTree(String path, String name, String icon, String id, String pId, List<MenuTree<T>> child, String order) {
        this.path = path;
        this.name = name;
        this.icon = icon;
        this.id = id;
        this.pId = pId;
        this.child = child;
        this.order = order;
    }

    /**
     * 节点的子节点
     */
    private List<MenuTree<T>> child = new ArrayList<>();

    public MenuTree() {
        super();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}