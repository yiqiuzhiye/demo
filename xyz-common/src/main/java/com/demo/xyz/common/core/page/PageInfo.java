package com.demo.xyz.common.core.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 
 * 
 * Page中记录的类型.
 * 
 */
public class PageInfo implements Serializable {

	private static final long serialVersionUID = 2468093376898910699L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// 公共变量
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	private Integer pageNum;
	private Integer pageSize;
	private String orderBy = null;
	private String order = null;

	public PageInfo(Integer pageNum, Integer pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;

	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public PageInfo() {
		super();
	}

	@Override
	public String toString() {
		return "PageInfo [pageNum=" + pageNum + ", pageSize=" + pageSize + ", orderBy=" + orderBy + ", order=" + order
				+ "]";
	}

}
