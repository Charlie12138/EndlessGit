# SpringMVC

:one:@RequestMapping可以修饰方法也可以修饰类 

* 类定义处：提供初步的请求映射信息。相对于WEB应用的根目录
* 方法处：提供进一步的细分映射信息，相对于类定义处的URL。若类定义处未标注@RequestMapping，则方法处标注的@RequestMapping相对于WEB应用的根目录。

:two:

```
@RequestMapping(value = "/testMethod", method = RequestMethod.POST)

method指定请求方法
```

:three:

```
@RequestMapping(value = "/testParamsAndHeaders", params = {"username", "age != 10"}, headers = 					{"Accept-Language=zh-CN,zh;q=0.9"})
params是参数
headers是报头？
```

:four:

```
@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println(id);
		return SUCCESS;
	}
@PathVariable可以映射URL中的占位符到目标方法的参数中
```

:five:

```
@RequestParam 来映射请求参数. 
value 值即请求参数的参数名 
required 该参数是否必须，默认为 true
defaultValue 请求参数的默认值
@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String un, @RequestParam(value = "age", required = false, defaultValue = "0") Integer age){
		System.out.println("username:" + un + ", age:" + age);
		return SUCCESS;
	}
```

:six:

```
	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println(user);
		return SUCCESS;
	}
	SpringMVC会按请求参数名和POJO属性名进行自动匹配，自动为该对象填充属性值，支持级联属性，如user.username, user.address.city
	
```

:seven:

```
	@RequestMapping("/testServletAPI")
	public void testServletAPI(HttpServletRequest request, HttpServletResponse response, Writer 		writer) throws IOException {
		System.out.println("request:" + request + ", response:" + response);
		writer.write("hello world");
	}
  可以使用Servlet原生的API作为目标方法的参数

```

:eight:

```
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String mn = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(mn);
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}
	目标方法的返回值可以是ModelAndView类型，其中可以包括试图和模型信息，SpringMVC会把ModelAndView的model中数据放到request域对象中。
```

:nine:

```
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("Tom", "Jerry", "Mike"));
		return SUCCESS;
	}
目标方法可以添加Map/ModelMap/Model类型的参数
```

:keycap_ten:

```
@SessionAttributes(value = {"user"}, types = String.class)
@RequestMapping("/springMvc")
@Controller
public class SpringMvcServlet {
	private static final String SUCCESS = "success";
	@RequestMapping("/testSessionAttribute")
	public String testSessionAttribute(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		map.put("user", new User("liqinglin", "123113"));
		map.put("hhh", "kkkkk");
		return SUCCESS;
	}
}

  	request user:${requestScope.user}
    <br>
    session user:${sessionScope.user}
    <br>
    request string:${requestScope.hhh}
    <br>
    session string:${sessionScope.hhh}
    
    除了可以通过属性名指定需要方法到会话中的属性外，还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中
```

:one::one:

```
@ModelAttribute
	public void getUser(@RequestParam(value="id", required = false) Integer id, Map<String, Object> map){
		User user = new User(1, 12, "lql", "12121");
		System.out.println("从数据库获取对象"+user);
		map.put("user", user);

	}
    有@ModelAttribute标记的方法会在每个目标方法执行之前被SpringMVC调用
    
    /**
	 * 运行流程:
	 * 1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象, 把对象放入到了 Map 中. 键为: user
	 * 2. SpringMVC 从 Map 中取出 User 对象, 并把表单的请求参数赋给该 User 对象的对应属性.
	 * 3. SpringMVC 把上述对象传入目标方法的参数. 
	 * 注意: 在 @ModelAttribute 修饰的方法中, 放入到 Map 时的键需要和目标方法入参类型的第一个字母小写		*的字符串一致!
	 */
    @RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user) {
		System.out.println("修改后：" + user);
		return SUCCESS;
	}
```

 **SpringMVC 确定目标方法 POJO 类型入参的过程**

1.确定一个 key

​	1). 若目标方法的 POJO 类型的参数没有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的				小写
	2). 若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值.

2.在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入

​	 1). 若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1 确定的 key 一致, 则会获取到. 

3.若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰,  若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常. 

4.若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则

 会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数

5.SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中. 

:one::two:

```
xxx-servlet.xml配置：
	<!-- 配置直接转发的页面 -->
	<!-- 可以直接相应转发的页面, 而无需再经过 Handler 的方法.  -->
	<mvc:view-controller path="/success" view-name="success"/>
	
	<!-- 在实际开发中通常都需配置 mvc:annotation-driven 标签 -->
	<mvc:annotation-driven></mvc:annotation-driven>

```

:one::three:制作自定义视图：

```
	<!-- 配置视图  BeanNameViewResolver 解析器: 使用视图的名字来解析视图 -->
	<!-- 通过 order 属性来定义视图解析器的优先级, order 值越小优先级越高 -->
	<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
		<property name="order" value="100"></property>
	</bean>
```



```
@Component
public class HelloView implements View {

   @Override
   public String getContentType() {
      return "text/html";
   }

   @Override
   public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
      httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
      httpServletResponse.setCharacterEncoding("UTF-8");
      httpServletResponse.getWriter().print("hello view：" + new Date());
   }
}
```

:one::four:

@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 过 @Autowired的使用标注到成员变量时不需要有set方法，请注意@Autowired 默认按类型匹配的 

:one::five:

```
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("lastName");
	}
	lastName将不会存入数据库
```

:one::six:

```
数据校验
1.注解形式
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;

	@NumberFormat(pattern="#,###,###.#")
	private Double salary;
```

1. 数据类型转换

2. 数据类型格式化

3. 数据校验. 

    1). 如何校验 ? 注解 ?
    	①. 使用 JSR 303 验证标准
    	②. 加入 hibernate validator 验证框架的 jar 包
    	③. 在 SpringMVC 配置文件中添加 <mvc:annotation-driven />
    	④. 需要在 bean 的属性上添加对应的注解
    	⑤. 在目标方法 bean 类型的前面添加 @Valid 注解
    2). 验证出错转向到哪一个页面 ?
    注意: 需校验的 Bean 对象和其绑定结果对象或错误对象时成对出现的，它们之间不允许声明其他的入参
    3). 错误消息 ? 如何显示, 如何把错误消息进行国际化

:one::seven:字符串转换为employee类型

```
<form action="testConversionServiceConverer" method="POST">
		<!-- lastname-email-gender-department.id 例如: GG-gg@atguigu.com-0-105 -->
		Employee: <input type="text" name="employee"/>
		<input type="submit" value="Submit"/>
</form>
```

```
@RequestMapping("/testConversionServiceConverter")
	public String testConverter(@RequestParam("employee") Employee employee) {
		System.out.println("save:" + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
```



1️⃣:eight:

文件上传：

```
pom.xml配置：
<!--文件上传-->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.2.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.6</version>
        </dependency>

springmvc.xml配置：
<!--配置MultipartResolver-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>
        <property name="maxUploadSize" value="1024000"/>
    </bean>

servlet配置：
@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc,
								 @RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("desc: " + desc);
		System.out.println("OriginalFilename: " + file.getOriginalFilename());
		System.out.println("InputStream: " + file.getInputStream());
		return "success";
	}
```

:one::nine:

* 需要进行 Spring 整合 SpringMVC 吗 ?
* 还是否需要再加入 Spring 的 IOC 容器 ?
* 是否需要再 web.xml 文件中配置启动 Spring IOC 容器的 ContextLoaderListener ?

   1. 需要: 通常情况下, 类似于数据源, 事务, 整合其他框架都是放在 Spring 的配置文件中(而不是放在 SpringMVC 的配置文件中).
       实际上放入 Spring 配置文件对应的 IOC 容器中的还有 Service 和 Dao. 
   2. 不需要: 都放在 SpringMVC 的配置文件中. 也可以分多个 Spring 的配置文件, 然后使用 import 节点导入其他的配置文件
* 问题: 若 Spring 的 IOC 容器和 SpringMVC 的 IOC 容器扫描的包有重合的部分, 就会导致有的 bean 会被创建 2 次.

			解决:
		1. 使 Spring 的 IOC 容器扫描的包和 SpringMVC 的 IOC 容器扫描的包没有重合的部分. 
		2. 使用 exclude-filter 和 include-filter 子节点来规定只能扫描的注解
	

```
springmvc.xml
<context:component-scan base-package="com.atguigu.springmvc" use-default-filters="false">
		<context:include-filter type="annotation" 
			expression="org.springframework.stereotype.Controller"/>
		<context:include-filter type="annotation" 
			expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
</context:component-scan>

beans.xml
<context:component-scan base-package="com.atguigu.springmvc">
		<context:exclude-filter type="annotation" 
			expression="org.springframework.stereotype.Controller"/>
		<context:exclude-filter type="annotation" 
			expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
	</context:component-scan>
```

:two::zero:

1. 数据类型转换

2. 数据类型格式化

3. 数据校验. 
    1). 如何校验 ? 注解 ?
          ①. 使用 JSR 303 验证标准
          ②.引入 hibernate validator 验证框架依赖

  ```
  <dependency>
     <groupId>org.hibernate</groupId>
     <artifactId>hibernate-validator</artifactId>
     <version>6.0.1.Final</version>
  </dependency>
  ```

  ​        ③. 在 SpringMVC 配置文件中添加 <mvc:annotation-driven />
           ④. 需要在 bean 的属性上添加对应的注解（@Past, @Email, @NotEmpty······）
          ⑤. 在目标方法 bean 类型的前面添加 @Valid 注解
  2). 验证出错转向到哪一个页面 ?
  注意: 需校验的 Bean 对象和其绑定结果对象或错误对象时成对出现的，它们之间不允许声明其他的入参
  3). 错误消息 ? 如何显示, 如何把错误消息进行国际化

```
@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String input(@Valid Employee employee, BindingResult bindingResult, Map<String, Object> map) {
		System.out.println("save:" + employee);
		if (bindingResult.getErrorCount() > 0) {
			System.out.println("出错了");
			for(FieldError error : bindingResult.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			map.put("departments", departmentDao.getDepartments());
			return "input";
		}
		employeeDao.save(employee);
		return "redirect:/emps";
	}
```

:two::one:返回Json

```
1.导入依赖
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.9.6</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.6</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.9.6</version>
        </dependency>
 2.测试方法
 @ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson(){
		return employeeDao.getAll();
	}
```

:two::two:拦截器

```
public class FirstInterceptor implements HandlerInterceptor {
	/*该方法在目标方法之前被调用.
	 * 若返回值为 true, 则继续调用后续的拦截器和目标方法. 
	 * 若返回值为 false, 则不会再调用后续的拦截器和目标方法. 
	 * 
	 * 可以考虑做权限. 日志, 事务等. 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("[FirstInterceptor] preHandle");
		return true;
	}
	/**
	 * 调用目标方法之后, 但渲染视图之前. 
	 * 可以对请求域中的属性或视图做出修改. 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("[FirstInterceptor] postHandle");
	}
	/**
	 * 渲染视图之后被调用. 释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("[FirstInterceptor] afterCompletion");
	}
}
```

:two::three:springmvc运行流程图

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180731165724.png)





[练习项目源码1](https://github.com/Charlie12138/EndlessGit/tree/master/protectProject/springmvc2)

[练习项目源码2](https://github.com/Charlie12138/EndlessGit/tree/master/protectProject/springmvc3)