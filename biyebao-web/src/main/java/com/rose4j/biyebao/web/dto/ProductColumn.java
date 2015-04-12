package com.rose4j.biyebao.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductColumn<T> {
	private String title;
	private String titleDesc;
	private List<T> contents = new ArrayList<T>();

	public ProductColumn(String title) {
		super();
		this.title = title;
	}

	public ProductColumn(String title, List<T> paras) {
		super();
		this.title = title;
		addContents(paras);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<T> getContents() {
		return contents;
	}

	public void addContent(T t) {
		contents.add(t);
	}

	private void addContents(List<T> paras) {
		for (T t : paras) {
			contents.add(t);
		}
	}

	public String getTitleDesc() {
		return this.title + "设计";
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}

	@Override
	public String toString() {
		return "ProductColumn [title=" + title + ", contents=" + contents + "]";
	}

}
