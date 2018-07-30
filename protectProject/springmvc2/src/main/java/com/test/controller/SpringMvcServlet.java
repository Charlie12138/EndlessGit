package com.test.controller;

import com.test.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@SessionAttributes(value = {"user"}, types = String.class)
@RequestMapping("/springMvc")

@Controller
public class SpringMvcServlet {
	private static final String SUCCESS = "success";

	@RequestMapping("/testRedirect")
	public String testRedirect() {
		System.out.println("testRedirect");
		return "redirect:/index.jsp";
	}

	@RequestMapping("/testView")
	public String testView() {
		System.out.println("test view");
		String helloView = "helloView";
		return helloView;
	}

	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user) {
		System.out.println("修改后：" + user);
		return SUCCESS;
	}


	@ModelAttribute
	public void getUser(@RequestParam(value="id", required = false) Integer id, Map<String, Object> map){
		User user = new User(1, 12, "lql", "12121");
		System.out.println("从数据库获取对象"+user);
		map.put("user", user);

	}

	@RequestMapping("/testSessionAttribute")
	public String testSessionAttribute(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		//map.put("user", new User("liqinglin", "123113"));
		map.put("hhh", "kkkkk");
		return SUCCESS;
	}


	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("Tom", "Jerry", "Mike"));
		return SUCCESS;
	}


	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		ModelAndView modelAndView = new ModelAndView(SUCCESS);
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}


	@RequestMapping("/testServletAPI")
	public void testServletAPI(HttpServletRequest request, HttpServletResponse response, Writer writer) throws IOException {
		System.out.println("request:" + request + ", response:" + response);
		writer.write("hello world");
	}


	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println(user);
		return SUCCESS;
	}

	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue ("JSESSIONID") String JS) {
		System.out.println("Accept-Language" + JS);
		return SUCCESS;
	}

	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
		System.out.println("Accept-Language" + al);
		return SUCCESS;
	}

	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String un, @RequestParam(value = "age", required = false, defaultValue = "0") Integer age){
		System.out.println("username:" + un + ", age:" + age);
		return SUCCESS;
	}

	@RequestMapping(value="/testRest/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable("id") Integer id){
		System.out.println("test put:" + id);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping(value="/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id") Integer id){
		System.out.println("test delete:" + id);
		return SUCCESS;
	}

	@RequestMapping(value="/testRest", method = RequestMethod.POST)
	public String testRest(){
		System.out.println("test POST");
		return SUCCESS;
	}

	@RequestMapping(value="/testRest/{id}", method = RequestMethod.GET)
	public String testRest(@PathVariable("id") Integer id){
		System.out.println("test Get:" + id);
		return SUCCESS;
	}

	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println(id);
		return SUCCESS;
	}

	@RequestMapping("/testAnt/*/abc")
	public String testAnt() {
		System.out.println("testAnt");
		return SUCCESS;
	}



	@RequestMapping(value = "/testParamsAndHeaders", params = {"username", "age != 10"}, headers = {"Accept-Language=zh-CN,zh;q=0.9"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}




	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}

	@RequestMapping("/springMvcServlet")
	public String springMvcServlet(){
		System.out.println("springMvcServlet");
		return SUCCESS;
	}
}
