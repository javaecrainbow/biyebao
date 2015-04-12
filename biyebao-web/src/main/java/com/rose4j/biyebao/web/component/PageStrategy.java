package com.rose4j.biyebao.web.component;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public enum PageStrategy {
	PRODUCT {
		@Override
		public String getPageUrl(Properties properties) {
			String url=properties.getProperty("pageUrl");
			System.out.println(url);
			if(StringUtils.indexOf(url, "-")>-1){
			return url.replaceAll("\\d+.html","pageNum")+".html";
			}else{
				//column/ASP.html
				return url.substring(0, url.indexOf(".html"))+"-"+"pageNum.html";
			}
		}
	};
	 public abstract String getPageUrl(Properties properties);
}
