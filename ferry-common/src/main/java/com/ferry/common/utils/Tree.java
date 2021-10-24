package com.ferry.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述：通用树结构
 * <p>
 * 作者：Ostrich Hu
 * 时间：2019/10/9 11:11
 */
@Data
public class Tree<T> {

    /**
     * 节点ID
     */
    private String id;

    /**
     * 显示节点文本
     */
    private String label;

    private String icon;

    /**
     * 节点状态，open closed
     */
    private Map<String, Object> state;

    /**
     * 节点是否被选中 true false
     */
    private boolean checked = false;

    /**
     * 节点属性
     */
    private Map<String, Object> attributes;

    /**
     * 节点的子节点
     */
    private List<Tree<T>> children = new ArrayList<>();

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 是否有父节点
     */
    private boolean hasParent = false;

    /**
     * 是否有子节点
     */
    private boolean hasChildren = false;

    /**
     * 排序字段
     */
    private String order;

    public Tree(String id, String label, String icon, Map<String, Object> state, boolean checked,
                Map<String, Object> attributes, List<Tree<T>> children, String parentId, boolean hasParent,
                boolean hasChildren, String order) {
        this.id = id;
        this.label = label;
        this.icon = icon;
        this.state = state;
        this.checked = checked;
        this.attributes = attributes;
        this.children = children;
        this.parentId = parentId;
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
        this.order = order;
    }

    public Tree() {
        super();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}