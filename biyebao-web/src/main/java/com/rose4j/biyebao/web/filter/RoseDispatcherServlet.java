package com.rose4j.biyebao.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

public class RoseDispatcherServlet extends DispatcherServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4422498304147640100L;

	@Override
	protected void doService(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("doService");
		super.doService(arg0, arg1);
	}

	@Override
	protected void doDispatch(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("doDispatch");
		super.doDispatch(arg0, arg1);
	}
}
