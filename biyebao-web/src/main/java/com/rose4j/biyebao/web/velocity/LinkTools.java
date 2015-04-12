package com.rose4j.biyebao.web.velocity;

import org.apache.velocity.tools.view.LinkTool;
import org.springframework.util.StringUtils;
public class LinkTools extends LinkTool {
 public String getPageUrl(int page,String pageUrl){
	 if(StringUtils.isEmpty(pageUrl)){
		 return "#";
	 }
	 return pageUrl.replaceAll("pageNum", page+"");
 }
}
