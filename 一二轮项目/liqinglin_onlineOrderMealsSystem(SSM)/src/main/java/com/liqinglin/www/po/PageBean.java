package com.liqinglin.www.po;

import java.util.List;

/**
 * 分页的实体类
 */
public class PageBean<T> {    //泛型类型参数作用于类上的时候主要是对多个字段及方法签名之间的类型约束。
	private int pageNum; // 当前页数
	private int pageSize; // 每页显示的数据条数
	private int totalRecord; // 从数据库搜索到的总记录数
	private int totalPage; // 总页数
	private int startIndex; // 在数据库查到的数据要从第几行开始的索引(对应limit语句中参数：···limit "pageSize" offset "startIndex")
	private List<T> list; // 每页要显示的数据放在list中
	private int start; // 每页中可以看到的第一个页码
	private int end; // 每页中可以看到的最后一个

	/**
	 * 构造方法获得pageNum:当前页数；pageSize:每页数据数量；totalRecord:总数据量
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param totalRecord
	 */
	public PageBean(int pageNum, int pageSize, int totalRecord) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;

		/**
		 * 计算总页数
		 */
		if (totalRecord % pageSize == 0) {
			this.totalPage = totalRecord / pageSize;
		} else {
			this.totalPage = (totalRecord / pageSize) + 1;
		}

		this.startIndex = (pageNum - 1) * pageSize; // （-1是因为行数跟数组一样是从0开始的）
		this.start = 1;
		this.end = 3;

		/**
		 * 显示页数的算法
		 */
		if (totalPage <= 3) { // 页数<=3
			this.end = totalPage;
		} else { // 页数>3
			this.start = pageNum - 1;
			this.end = pageNum + 1;

			if (start == 0) { // 如果是第一页，pageNum - 1 = 0
				this.start = 1;
				this.end = 3;
			}

			if (end > this.totalPage) { // 如果是最后一页，pageNum + 1 > totalPage
				this.start = totalPage - 2;
				this.end = totalPage;
			}
		}
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

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
