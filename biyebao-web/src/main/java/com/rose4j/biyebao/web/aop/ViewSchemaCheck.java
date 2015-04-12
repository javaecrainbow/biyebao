package com.rose4j.biyebao.web.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ViewSchemaCheck {
//@Before("execution(* com.rose4j.biyebao.web.controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
@Around("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..))")
	public void test(){
		System.out.println("已经拦截=====================");
	}
@Before("execution(* com.rose4j.biyebao.web.controller.ProductController.listForHandler(..)) && args(..,request)")
	public void test2(HttpServletRequest request){
	request.setAttribute("test", "test");
		System.out.println("已经拦截2=====================");
	}
}
