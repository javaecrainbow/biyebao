package com.rose4j.biyebao.domain;

import java.io.Serializable;

public class Page implements Serializable {
	public static int SIZE=10;
	public static int Range=5;
	private   int start;
	private  int end;
	private int currentPage;
	private String pageUrl;
	private int total;
	private int totalPage;
	
	
	public Page(int currentPage, String pageUrl) {
		super();
		this.currentPage = currentPage;
		this.pageUrl = pageUrl;
	}
	public int getStart() {
		
		if(currentPage-(Range-1)/2<1){
			return 1;
		}else{
			return currentPage-(Range-1)/2;
		}
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		if(getStart()+Range-1>getTotalPage()){
			return getTotalPage();
		}else{
		return getStart()+Range-1;
		}
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getTotalPage() {
		return getTotal()%SIZE==0?getTotal()/SIZE:getTotal()/SIZE+1;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "Page [start=" + start + ", end=" + end + ", pageUrl=" + pageUrl
				+ "]";
	}

}
