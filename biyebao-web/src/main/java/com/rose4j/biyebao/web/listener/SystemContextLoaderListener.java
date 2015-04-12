package com.rose4j.biyebao.web.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

/**
 * 管理spring的初始化
 * @author rose4j
 *2014年9月25日
 */
public class SystemContextLoaderListener extends ContextLoaderListener {
@Override
public void contextInitialized(ServletContextEvent event) {
	System.out.println("初始化系统的上下文，初始化所有的bean");
	super.contextInitialized(event);
}
}
