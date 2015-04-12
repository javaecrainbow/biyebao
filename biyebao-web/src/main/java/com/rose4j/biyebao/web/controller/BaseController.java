package com.rose4j.biyebao.web.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.rose4j.biyebao.domain.Page;
import com.rose4j.biyebao.web.component.PageStrategy;

public abstract class BaseController {

	public String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ip) || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	public boolean validCheck(String page){
		if(NumberUtils.isNumber(page)){
			return false;
		}
		return true;
	}
	public Page buildPageCommand(HttpServletRequest request,String page){
		Integer int_page = Integer.parseInt(page);
		Properties prop = new Properties();
		prop.setProperty("pageUrl",getRequestUrl(request));
		return new Page(int_page,PageStrategy.PRODUCT.getPageUrl(prop) );
	}
	
	public String getRequestUrl(HttpServletRequest request){

		String requestUrl=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getServletPath();
		if(StringUtils.isNotEmpty(request.getQueryString())){
			requestUrl.concat("?").concat(request.getQueryString());
		}
		return requestUrl;
	}
}
