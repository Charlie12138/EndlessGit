Servlet是java服务器端编程，不同于我们之前写的一般的java应用程序，Servlet程序是运行服务器上的。

**==映射过程==：** 

1.==http://localhost:8080/HelloWorldServlet/HelloWorld==中Tomcat发现要访问/HelloWorld资源


2.-->找web.xml

```
 <servlet>
  	<servlet-name>HelloWorld</servlet-name>
  	<servlet-class>First.HelloWorldServlet</servlet-class>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>HelloWorld</servlet-name>
  	<url-pattern>/HelloWorld</url-pattern>
  </servlet-mapping>
```
找到pattern为/HelloWorld后得到servlet-name为HelloWorld然后拿着名字向前找到匹配的名字然后向下找到servlet-class，然后实例化**First.HelloWorldServlet**这个类


3.-->根据用户请求调用**First.HelloWorldServlet**里的doGet()方法，然后响应给客户端


* ==我们可以将Servlet看作是嵌套了HTML代码的Java类，可以将JSP看作是嵌套了Java的HTML页面==
* 



==get与post方法之间的不同==
* 浏览器地址栏呈现的结果不同
* 真正的原意在于向服务器端发送请求时的形式是不同的
* #### get请求格式：
**http://localhost:8080/HelloWorldServlet/LoginServlet?username=1234&password=1234 HTTP/1.1**
* #### post请求格式：
**http://localhost:8080/HelloWorldServlet/LoginServlet HTTP/1.1**

**Connection:Keep-Alive


username = hello&password = word**
* 通过浏览器进行文件上传时一定要使用post方式，不能使用get方式
* 通过浏览器地址栏输入网址的方式来访问服务器资源，全部使用的是get方法请求的。
* ==生成页面的流程==
```
graph LR
A[浏览器客户端]
B[服务器Tomcat]
C[Servlet]
A-->B
B-->A
B-->C
```
==JSP执行过程：==
![image](https://raw.githubusercontent.com/Charlie12138/testgit/master/666.png)
![image](https://raw.githubusercontent.com/Charlie12138/testgit/master/777.png)
* JSP最终都会转换成servlet执行
* 
* 注释：
* 
<！--  HTML注释 -->  可见

<%-- JSP注释 --%>    不可见

* 声明：

<%! int a = 3; %>


* JSP会将声明转换为成员变量，而将脚本段<% int b = 3 %>转换为方法的局部变量，Servlet是单实例的，这样成员变量的值就只有一个，每个用户都会访问到它，而脚本段中的值就只有一个，每个用户访问的时候各有一份，互不影响。
* JSP包含（include）相当于swing中的JPanle可以加到JFrame,只不过JSP是页面包含页面
* <jsp:forward>指令用于转向页面，在该指令后面的所有代码都没有机会执行，因为页面流程已经转向另一个界面。
* getParameter方法是getParameterValues方法的特例，表示请求参数值只有一个，如果请求参数值有多个，请使用getParameterValues方法。在多选框的情况下，需要使用getParameterValues方法来获取用户所选择的多个复选框的值。
* request的setAtttibute与getAttribute一般都是成对出现的，首先通过setAttribute方法设置属性与属性值，然后通过getAttribute方法根据属性获取到与该属性对应的对象值(==获取到之后需要进行向下类型转换，将属性值转换为真正的对象==)。setAttribute与getAttribute方法都是在服务器内部执行的，客户端不知道服务器是否执行过这两个方法。
* request的getParameter方法的作用是获取到客户端通过表单或url请求参数所发送过来的参数值，是客户端与服务器端之间的交互，服务器端要想获取到客户端发送过来的数据，就需要使用getParameter方法来获取。没有与getParameter方法对应的setParameter.
* request对象内数据的存活范围就是在request对象的存在范围内，当客户端向服务器端发送一个请求，服务器向客户端返回一个响应后，该请求对象就被销毁，之后服务器发送新的请求和之前的请求也就没有关系。
* session对象内数据的存活范围也就是session对象的存活范围（==只要浏览器不关闭，session对象就会一直存在==），因此在同一个浏览器窗口中，无论向服务器端发送多少个请求，session对象只有一个。
* application（应用对象）：存活范围最大的对象，只要服务器没有关闭，application对象中的数据就会一直存在。在整个服务器运行过程中，application对象只有一个。
* request、session、application这3个对象的范围是逐个增加的：request只在一个请求的范围内；==session实在浏览器窗口的范围内==；application则是在整个服务器的运行过程中。
* application的getRealPath方法会返回资源在服务器资源上的绝对路径。
* HttpSerlvetResponse对象的sendRedirect(String location)方法称作重定向。如果location地址前面加上"/",则表示相对于Serlvet容器的根来请求，即：http://localhost:8080,如果location地址前没有加上"/",则表示相对于当前请求的URI来寻求地址。
* RequestDispatcher的forward(request,response)方法称作请求转发。
* ==请求转发与重定向的区别==

1.请求转发：整个过程处于同一个请求中。客户端不知道服务器内运作。

2.重定向：客户端会发送两个请求。通过返回的第二个请求知道服务器即将进行运作会在地址栏显示下一个界面名称。

3.RequestDispatcher是通过调用HttpServletRequest对象的getRequestDispatcher()方法得到的，是属于请求对象的方法。
```
RequestDispatcher rd = req.getRequestDispatcher("xxx.jsp");
rd.forward(req,resp);
```

4.sendRedirect()是HttpServletResponse对象的方法，即响应对象的方法，既然调用了响应方法对象的方法，那就表明整个请求过程已经结束了，服务器开始向客户端返回执行的结果。
```
resp.sendRedirect("xxx.jsp");
```