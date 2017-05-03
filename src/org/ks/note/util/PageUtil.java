package org.ks.note.util;

public class PageUtil {
	private int currentPage;//第几页
	private int pageSize;//每页记录数
	
	public int getBegin(){
		int begin=(currentPage-1)*pageSize;
		return begin;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
