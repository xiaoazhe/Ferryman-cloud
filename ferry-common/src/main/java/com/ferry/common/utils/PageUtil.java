package com.ferry.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author HuTongFu
 * @description: 分页参数
 * @since 2019/6/6 13:39
 */
@Data
public class PageUtil<T> {

    private static final long serialVersionUID = 7473512058265628720L;

    private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;

    private List<T> result;

    public PageUtil(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        this.endRow = pageNum * pageSize;
    }

    /**
     * 分页
     *
     * @param result   列表数据
     * @param total    总记录数
     * @param pageSize 每页记录数
     * @param pageNum  当前页数
     */
    public PageUtil(List<T> result, long total, int pageSize, int pageNum) {
        this.result = result;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        this.endRow = pageNum * pageSize;
        this.pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
    }

    /**
     * 上一页索引值
     *
     * @return
     */
    @JsonIgnore
    public int getPrev() {
        if (pageNum == 1) {
            return pageNum;
        } else {
            return pageNum - 1;
        }
    }

    /**
     * 下一页索引值
     *
     * @return
     */
    public int getNext() {
        if (pageNum == pages) {
            return pageNum;
        } else {
            return pageNum + 1;
        }
    }
}
