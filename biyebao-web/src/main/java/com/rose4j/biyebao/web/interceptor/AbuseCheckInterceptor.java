package com.rose4j.biyebao.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AbuseCheckInterceptor extends HandlerInterceptorAdapter implements InitializingBean,Ordered{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("AbuseCheckInterceptor::preHandler=============");
		return super.preHandle(request, response, handler);

	}
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
