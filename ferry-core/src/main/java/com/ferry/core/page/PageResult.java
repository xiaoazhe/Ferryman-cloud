package com.ferry.core.page;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
public class PageResult {
	/**
	 * 当前页码
	 */
	private int pageNum;
	/**
	 * 每页数量
	 */
	private int pageSize;
	/**
	 * 记录总数
	 */
	private long totalSize;
	/**
	 * 页码总数
	 */
	private int totalPages;
	/**
	 * 分页数据
	 */
	private List<?> content;

	public PageResult() {
	}

	public PageResult(Page page) {
		this.content = page.getRecords();
		this.totalPages = (int) page.getTotal() / (int) page.getSize();
		this.pageSize = (int) page.getSize();
		this.pageNum = (int) page.getCurrent();
		this.totalSize = page.getTotal();
	}

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<?> getContent() {
		return content;
	}
	public void setContent(List<?> content) {
		this.content = content;
	}
}
