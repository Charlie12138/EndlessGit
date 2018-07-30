# BUG:artificial_satellite:

#### 1.jsp放入了Web-INF中出现404



#### 2.用IDEA创建maven框架下的mybatis项目的bug:

:one:把src下的main/java删掉建了一个com.lql.test1文件夹，在运行时conf.xml里的

```
 	<mappers>
        <mapper resource="com/lql/test1userMapper.xml"/>
    </mappers>
```

​	resourse说找不到。

	##### 原因：com.lql.test1被作为根目录，编译后路径出现了问题

##### 解决：建一个java文件夹把com.lql.test1放进去

:two:在实体类里没建无参的构造方法：

```
Cause: org.apache.ibatis.executor.ExecutorException: No constructor found in com.lql.test1.User matching [java.lang.Integer, java.lang.String, java.lang.Integer]
```

猜想大体上实例化流程就是，resultMap会去调用Bean的默认构造函数，然后将所有的成员变量和查询结果的列名形成映射，当你显示地指定了一个构造函数，然而它又不能将查询结果和显示指定的构造函数的参数形成映射的时候就会抛出异常

```
No constructor found in com.tszhao.dao.User matching [java.lang.Integer, java.lang.String, java.lang.Integer]
```

(它想找的是传入Integer的构造函数， 而你只有传入int的构造函数)所以要么不显式地指定构造函数，使用自动生成的默认构造函数要么在自己指定的构造函数中使用包装类型（当然也可以直接在你的那行构造函数上面显示地添加一个无参构造函数，不过看起来好像是多此一举）

:three:

```
25-Jul-2018 22:39:02.846 警告 [http-nio-8080-exec-6] org.springframework.web.servlet.DispatcherServlet.noHandlerFound No mapping found for HTTP request with URI [/helloworld] in DispatcherServlet with name 'dispatcher'



原因：没有在controller里加上@Controller的注释
```

:four:springMVC视频测试**HiddenHttpMethodFilter** 操作出现的BUG

```
Type: Status Report

Message: JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS

Description: The method received in the request-line is known by the origin server but not supported by the target resource.

解决方法加注解@ResponseBody

	@ResponseBody
	@RequestMapping(value="/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id") Integer id){
		System.out.println("test delete:" + id);
		return SUCCESS;
	}
```

:five:springMVC视频测试国际化出现乱码 ==`???i18n.username???`== ，解决方法：在setting中把编码设置为UTF-8。

浏览器的语言要移到顶部才能让语言成功切换。

:six:springMVC自定义视图，没有在xxx-servlet.xml配置文件配自动扫描：

```
<context:component-scan base-package="com.test.views">< /context:component-scan> 
```

导致404错误。

:seven:

```
使用@Autowired要在springmvc.xml加包扫描才能正常使用。
```

:eight:

```
405
Type Status Report

Message Request method 'POST' not supported

Description The method received in the request-line is known by the origin server but not supported by the target resource.

原因： <url-pattern>/</url-pattern>没有加星号
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
  </filter>
  
  <filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/</url-pattern>
  </filter-mapping>
  
  
```

:nine:

```
springmvc数据格式化生日日期格式应该导入
import java.sql.Date;

Type ：Status Report

Description : The server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).
```

