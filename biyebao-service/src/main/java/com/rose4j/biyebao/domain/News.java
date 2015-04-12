package com.rose4j.biyebao.domain;

import org.apache.commons.lang.StringUtils;

public class News {
private String title;
private String url;
private String id;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getShowTitle(){
	if(StringUtils.isNotBlank(title) && title.length()>50){
		return title.substring(0,45)+"...";
	}else{
		return title;
	}
}
public String getUrl() {
	return "http://bbs.biyebao.cn/Rose4j-BBS/posts/list/"+this.id+".page";
}
public void setUrl(String url) {
	this.url = url;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
@Override
public String toString() {
	return "News [title=" + title + ", url=" + url + ", id=" + id + "]";
}

}
