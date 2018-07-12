```
String a = "abc"; //不是空对象
boolean b = a.equals("abc"); //不会报错
String c = null; //空对象
boolean d = c.equals("abc"); //会报错
报不报错主要看调用equals方法的对象是不是null。
所以很多程序员会这么写程序：if ("".搜索equals(a))...而不写成if (a.equals(""))...
原因是a有可能是null。
```
### MVC设计模式：
M:Model模型

V:View视图 

C:Controller控制器 
### Javabean有以下几个特性：
* JavaBean是一个公共的（public）类
* JavaBean 有一个不带参数的构造方法
* JavaBean 通过set方法设置属性和get方法获取属性
* Jsp访问JavaBean的语法
> 导入JavaBean类
> 声明JavaBean对象
> 访问JavaBean属性
* 可以在<jsp:setProperty>中通过param参数为bean的属性动态赋值
```
<jsp:setProperty property = "age" name = "person" param = "helloworld"/>
```
* scope 属性决定了JavaBean对象存在的范围
 > page(默认值)
 > request
 > session
 > application
* 每一个Servlet都必须实现Servlet接口，GenericServlet是个通用的、不特定与任何协议的Servlet，它实现了Servlet接口，而HttpServlet继承于GenericServlet,因此HttpServlet也实现了Servlet接口，所以我们定义的Servlet只需要继承HttpServlet父类即可。
* Servlet接口中定义了一个service方法，HttpServlet对该方法进行了实现，实现方法就是==将ServletRequest与ServletResponse转换为HttpServletRequest与HttpServletResponse==.转换完毕后，会调用HttpServlet类中自己定义的service方法。在该service方法中，首先==获得请求的方法名==，然后根据方法名==调用对应的doXXX方法==，比如说请求方法为GET，那么就去调用doGET方法；请求方法为POST，就调用doPOST方法。
* 在HttpServlet类中所提供的的doGET、doPOST等方法都是直接返回错误信息，所以我们**需要在自己定义的Servlet类中override这些方法**。
* ##### 在下列时刻Servlet容器装载Servlet：
    > Servlet容器启动时自动装载某些Servlet

    > 在Servlet容器启动后，客户首次向Servlet发出请求
 
    > Servlet的类文件被更新后，重新装载Servlet
* Servlet被装载后，Servlet容器创建一个Servlet实例并且调用Servlet的init()方法进行初始化。在Servlet的整个生命周期中，init方法只会被调用一次。
* 某些Servlet在web.xml文件中只有<Servlet>元素而没有<servlet-mapping>元素，这样我们就无法通过常会在<servlet>元素中==配置一个<load-on-startup>子元素==，让容器在启动的时候自动加载该Servlet，并且调用其init方法完成一些全局性的初始化工作。
#### Servlet的多线程问题：
* ==Servlet==本身是==单实例的==，这样当有多个用户同时访问某个Servlet时，会访问该唯一的Servlet实例中的==成员变量==，如果对成员变量进行写入操作，那就会导致Servlet的多线程问题，即数据不一致。
* 解决Servlet多线程同步问题的最好方案：==去除实例变量，使用局部变量==。
### Session的运行机制图
![image](https://raw.githubusercontent.com/Charlie12138/testgit/master/999.png)
### request.getSession()
* session长驻在服务器内存里，session有id标识，一个session专供一个用户使用。
request只能存在于一次访问里，为了让每次访问区分开是哪个用户，所以request中会带上session的id标识，就是每个request都会属于一个session，一个session能为很多次request服务。类似于数据库的多对一关系
所以request.getSession的本质是使用request中的session id去找到这个用户对应的session
#### 过滤器（Filter）:
* Servlet过滤器本身并不产生请求和响应对象，它只提供过滤作用。
* Servlet过滤器能够在Servlet被调用之前检查Request对象，修改Request Header和Request内容。
* 在Servlet被调用之后检查Response对象，修改Response Header和Response内容。Servlet过滤器负责的Web组件可以是Servlet、jsp或HTML文件。、
#### 过滤器链式请求过程（FilterChain）：
```
graph LR
A(客户端)
B(服务器)
C[Filter1]
D[Filter2]
G(Servlet)
A-->B
B-->C
C-->D
D-->G
G-->D
D-->C
C-->B
B-->A
```

### EL表达式：
* ${param.username}
会依次调用pageContext.getAttribute("username") -> request.getAttribute("username") -> session.getAttribute("username") -> application.getAttribute("username")，只要找到某一个不为空的值就立刻返回。  搜索
* ${requestScope.username}
只返回request.getAttribute("username")
* 利用标签定义的变量名可以在EL表达式中使用，如${name1}就是从page/request/session/application中依次取值，直到取到为止，也可以直接${request.name1}在request中取值。 
 而利用标签定义的name属性并不能直接在EL表达式中使用 
* ${param.name} 等价于request.getParamter(“name”)，主要是服务器从页面或者客户端获取信息的方法。 
 对应的是${requestScope.name}等价于request.getAttribute(“name”),一般是从服务器传递结果到页面，在页面中取出服务器保存的值。
