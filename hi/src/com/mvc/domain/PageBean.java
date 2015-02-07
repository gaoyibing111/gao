package com.mvc.domain;

import net.sf.json.JSONObject;

public class PageBean {
	
	

	private int total;//总记录数
	private Object rows;//返回所用记录对象
	private Integer page;//当前第几页  
	private Integer pageSize;// 每页显示多少条数
	
	

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
}
