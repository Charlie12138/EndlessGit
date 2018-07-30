package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloServlet {
	/**
	 * 使用@RequestMapping注解映射请求的URL
	 * 返回值会通过视图解析器InternalResourceViewResolver解析为实际的物理视图prefix+returnvalue+suffix,然后做转发操作
	 * @return
	 */

	@RequestMapping("/helloworld")
	public String hello() {
		System.out.println("hello world");
		return "success"; //return “hello”：处理完该请求后返回的页面，此请求返回 hello.jsp页面。
	}
}
