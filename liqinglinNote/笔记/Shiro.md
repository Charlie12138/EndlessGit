#    					Shiro

* 1.Apache Shio是一个Java的安全（权限）框架。
* Shiro可以非常容易的开发出足够好的应用，可以应用在JavaSE和JavaEE环境
* Shiro可以完成：认证、授权、加密、会话管理、、与Web集成、缓存等。

 ## 一、Shiro实例

IDEA:spring+springmvc+shiro

### 1.1配置好spring+springmvc环境，引入shiro的maven依赖

```xml
<!-- Spring 整合Shiro需要的依赖 -->
    <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-core</artifactId>
      <version>1.2.1</version>
    </dependency>
    <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-web</artifactId>
      <version>1.2.1</version>
    </dependency>
    <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-ehcache</artifactId>
      <version>1.2.1</version>
    </dependency>
    <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-spring</artifactId>
      <version>1.2.1</version>
    </dependency>
```

### 1.2定义拦截器

对url进行拦截，如果还没有验证的需要验证，然后额外给用户赋予角色和权限。自定义的拦截器需要继承`AuthorizingRealm`并实现登录验证和赋予角色权限的两个方法。

```java
public class MyShiroRealm extends AuthorizingRealm {
	private static final String USER_NAME = "lqllql";
	private static final String PASSWORD = "123456";

	/**
	 * 授权
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Set<String> roleNames = new HashSet<>();
		Set<String> permissions = new HashSet<>();
		roleNames.add("admin"); //添加角色
		permissions.add("newPage.jhtml");  //添加权限
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	/**
	 * 登录验证
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		if (token.getUsername().equals(USER_NAME)) {
			//DecriptUtil.MD5(PASSWORD),util包下方法
            return new SimpleAuthenticationInfo(USER_NAME, DecriptUtil.MD5(PASSWORD), getName());
		} else {
			throw new AuthenticationException();
		}
	}
}
```

### 1.3 shiro配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
       default-lazy-init="true">

    <description>Shiro Configuration</description>

    <!-- Shiro's main business-tier object for web-enabled applications -->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="myShiroRealm" />
        <property name="cacheManager" ref="cacheManager" />
    </bean>

    <!-- 項目自定义的Realm -->
    <bean id="myShiroRealm" class="Realm.MyShiroRealm">
        <property name="cacheManager" ref="cacheManager" />
    </bean>

    <!-- Shiro Filter -->
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager" />
        <!--没有登录的用户请求需要登录的页面时自动跳转到登录页面，可配置也可不配置-->
        <property name="loginUrl" value="/login.jhtml" />
        <!--登录成功默认跳转页面，不配置则跳转至“/”，一般可以不配置，直接通过代码进行处理-->
        <property name="successUrl" value="/loginsuccess.jhtml" />
        <!--没有权限默认跳转的界面-->
        <property name="unauthorizedUrl" value="/error.jhtml" />
        <property name="filterChainDefinitions">
            <value>
                /index.jhtml = authc
                /login.jhtml = anon
                /checkLogin.json = anon
                /loginsuccess.jhtml = anon
                /logout.json = anon
                /** = authc
            </value>
        </property>
    </bean>

    <!-- 用户授权信息Cache -->
    <bean id="cacheManager" class="org.apache.shiro.cache.MemoryConstrainedCacheManager" />

    <!-- 保证实现了Shiro内部lifecycle函数的bean执行 -->
    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor" />

    <!-- AOP式方法级权限检查 -->
    <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
          depends-on="lifecycleBeanPostProcessor">
        <property name="proxyTargetClass" value="true" />
    </bean>

    <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
        <property name="securityManager" ref="securityManager" />
    </bean>

</beans>
```

#### filterChainDefinitions

* Shiro验证URL时，URL匹配成功变不再继续匹配查找（注意配置文件中的URL顺序），filterChainDefinitions的配置顺序为自上而下，以最上面的为准；

* 运行一个Web应用程序时,Shiro将会创建一些有用的默认Filter实例,并自动地在[main]项中，将它们置为可用自动地，可用的默认的Filter实例是被DefaultFilter枚举类定义的,枚举的名称字段就是可供配置的名称；

* 通常可将这些过滤器分为两组：

  anon,authc,authcBasic,user是第一组认证过滤器

  perms,port,rest,roles,ssl是第二组授权过滤器

  * 注意user和authc不同：当应当开启了rememberMe时，用户下次访问时可以是一个user，但绝不会是一个authc，因为authc是需要重新认证的。
  * user表示用户不一定已通过认证，只要曾被Shiro记住过登录状态的用户就可以正常发起请求，如rememberMe。（以前的用户登录时开启了rememberMe，然后关闭浏览器，下次再访问时他就是一个user，而不是authc）

* 举几个:chestnut::
  * /admin = authc, roles[admin] 表示用户必需已通过认证，并拥有admin角色才可以正常发起'/admin'请求.
  * /edit = authc, perms[admin, edit] 表示用户必需已通过认证，并拥有admin:edit权限才可以正常发起'/edit'请求
  * /home = user 表示用户不一定需要已经通过认证，只需要曾经被Shiro记住过登录状态就可以正常发起'/home'请求

* 各默认过滤器常用如下：

  * /admins/** = anon 无参,表示可匿名使用,可以理解为匿名用户或游客

  * /admins/user/** = authcBasic 无参,表示httpBasic认证 
  * /admins/user/** = user 无参,表示必须存在用户,当登入操作时不做检查 
  * /admins/user/** = ssl 无参,表示安全的URL请求,协议为https 
  * /admins/user/ `*` = perms[user:add:] 参数可写多个,多参时必须加上引号,且参数之间用逗号分割,如/admins/user/*=perms[“user:add:,user:modify:*”] 当有多个参数时必须每个参数都通过才算通过,相当于isPermitedAll()方法
  *  /admins/user/**=port[8081] 当请求的URL端口不是8081时,跳转到schemal://serverName:8081?queryString，其中schemal是协议http或https等,serverName是你访问的Host,8081是Port端口,queryString是你访问的URL里的?后面的参数 
  * /admins/user/** = rest[user] 根据请求的方法,相当于/admins/user/**=perms[user:method],其中method为post,get,delete等 
  * /admins/user/** = roles[admin]  参数可写多个,多个时必须加上引号,且参数之间用逗号分割,如/admins/user/**=roles[“admin,guest”] ，当有多个参数时必须每个参数都通过才算通过,相当于hasAllRoles()方法

### 1.4Controller

```java
public class UserController {

	@RequestMapping("/index.jhtml")
	public ModelAndView getIndex(HttpServletRequest request) {
		return new ModelAndView("index");
	}

	@RequestMapping("/exceptionForPageJumps.jhtml")
	public ModelAndView exceptionForPageJumps(HttpServletRequest request) {
		throw new BusinessException(LuoErrorCode.NULL_OBJ);
	}

	@RequestMapping(value = "/bussinessException.json", method = RequestMethod.POST)
	@ResponseBody
	public String bussinessException(HttpServletRequest request) throws Exception {
		throw new Exception();
	}

	//跳转到登录成功界面
	@RequestMapping("/login.jhtml")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping("/newPage.jhtml")
	public ModelAndView newPage() {
		return new ModelAndView("newPage");
	}

	@RequestMapping("/newPageNotAdd.jhtml")
	public ModelAndView newPageNotAdd() {
		return new ModelAndView("newPageNotAdd");
	}

	/**
	 * 验证用户名和密码
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/checkLogin.json", method = RequestMethod.POST)
	@ResponseBody
	public String checkLogin(String username, String password) {
		Map<String, Object> result = new HashMap<>();
		try{
			UsernamePasswordToken token = new UsernamePasswordToken(username, DecriptUtil.MD5(password));
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				token.setRememberMe(true);
				currentUser.login(token);
			}
		} catch (Exception e) {
			throw new BusinessException(LuoErrorCode.LOGIN_VERIFY_FAILURE);
		}
		result.put("success", true);
		return JSONUtils.toJSONString(result);
	}

	@RequestMapping(value = "/logout.json", method = RequestMethod.POST)
	@ResponseBody
	public String logout() {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return JSONUtils.toJSONString(result);
	}
}
```

### 1.5login.jsp代码

```html
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
<script src="<%=request.getContextPath()%>/static/bui/js/jquery-1.8.1.min.js"></script>
</head>

<body>
username: <input type="text" id="username"><br><br>  
password: <input type="password" id="password"><br><br>
登录
</body>

<script type="text/javascript">
$('#loginbtn').click(function() {
    var param = {
        username : $("#username").val(),
        password : $("#password").val()
    };
    $.ajax({ 
        type: "post", 
        url: "<%=request.getContextPath()%>" + "/checkLogin.json", 
        data: param, 
        dataType: "json", 
        success: function(data) { 
            if(data.success == false){
                alert(data.errorMsg);
            }else{
                //登录成功
                window.location.href = "<%=request.getContextPath()%>" +  "/loginsuccess.jhtml";
            }
        },
        error: function(data) { 
            alert("调用失败...."); 
        }
    });
});
</script>

</html>


```

## 二、跟我学shiro的学习笔记

**shiro不会去维护用户、维护权限；这些需要我们自己去设计/提供；然后通过相应的接口注入给shiro即可。**

### 1.subject

主体，代表当前“用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是Subject，如网络爬虫，机器人等；即一个抽象概念；所有的Subject都绑定到SecurityManager，与Subject的所有交互都会委托给SecurityManager；可以把Subject认为是一个门面；SecurityManager才是实际的执行者。

### 2.Realm

域，Shiro从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；可以把Realm看成DataSource，即安全数据源。

## 三、shiro集成spring

web.xml配置

```xml
<!-- Shiro Filter is defined in the spring application context: -->
	<!-- 
	1. 配置  Shiro 的 shiroFilter.  
	2. DelegatingFilterProxy 实际上是 Filter 的一个代理对象. 默认情况下, Spring 会到 IOC 容器中查找和 
	<filter-name> 对应的 filter bean. 也可以通过 targetBeanName 的初始化参数来配置 filter bean 的 id. 
	-->
    <filter>
        <filter-name>shiroFilter</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
        <init-param>
            <param-name>targetFilterLifecycle</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>shiroFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

spring-shiro.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- =========================================================
         Shiro Core Components - Not Spring Specific
         ========================================================= -->
    <!-- Shiro's main business-tier object for web-enabled applications
         (use DefaultSecurityManager instead when there is no web environment)-->
    <!--  
    1. 配置 SecurityManager!
    -->     
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="cacheManager" ref="cacheManager"/>
        <property name="authenticator" ref="authenticator"></property>
        
        <property name="realms">
        	<list>
    			<ref bean="jdbcRealm"/>
    			<ref bean="secondRealm"/>
    		</list>
        </property>
        
        <property name="rememberMeManager.cookie.maxAge" value="10"></property>
    </bean>

    <!-- Let's use some enterprise caching support for better performance.  You can replace this with any enterprise
         caching framework implementation that you like (Terracotta+Ehcache, Coherence, GigaSpaces, etc -->
    <!--  
    2. 配置 CacheManager. 
    2.1 需要加入 ehcache 的 jar 包及配置文件. 
    -->     
    <bean id="cacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
        <!-- Set a net.sf.ehcache.CacheManager instance here if you already have one.  If not, a new one
             will be creaed with a default config:
             <property name="cacheManager" ref="ehCacheManager"/> -->
        <!-- If you don't have a pre-built net.sf.ehcache.CacheManager instance to inject, but you want
             a specific Ehcache configuration to be used, specify that here.  If you don't, a default
             will be used.: -->
        <property name="cacheManagerConfigFile" value="classpath:ehcache.xml"/> 
    </bean>
    
    <bean id="authenticator" 
    	class="org.apache.shiro.authc.pam.ModularRealmAuthenticator">
    	<property name="authenticationStrategy">
    		<bean class="org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy"></bean>
    	</property>
    </bean>

    <!-- Used by the SecurityManager to access security data (users, roles, etc).
         Many other realm implementations can be used too (PropertiesRealm,
         LdapRealm, etc. -->
    <!-- 
    	3. 配置 Realm 
    	3.1 直接配置实现了 org.apache.shiro.realm.Realm 接口的 bean
    -->     
    <bean id="jdbcRealm" class="com.atguigu.shiro.realms.ShiroRealm">
    	<property name="credentialsMatcher">
    		<bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
    			<property name="hashAlgorithmName" value="MD5"></property>
    			<property name="hashIterations" value="1024"></property>
    		</bean>
    	</property>
    </bean>
    
    <bean id="secondRealm" class="com.atguigu.shiro.realms.SecondRealm">
    	<property name="credentialsMatcher">
    		<bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
    			<property name="hashAlgorithmName" value="SHA1"></property>
    			<property name="hashIterations" value="1024"></property>
    		</bean>
    	</property>
    </bean>

    <!-- =========================================================
         Shiro Spring-specific integration
         ========================================================= -->
    <!-- Post processor that automatically invokes init() and destroy() methods
         for Spring-configured Shiro objects so you don't have to
         1) specify an init-method and destroy-method attributes for every bean
            definition and
         2) even know which Shiro objects require these methods to be
            called. -->
    <!--  
    4. 配置 LifecycleBeanPostProcessor. 可以自定的来调用配置在 Spring IOC 容器中 shiro bean 的生命周期方法. 
    -->       
    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>

    <!-- Enable Shiro Annotations for Spring-configured beans.  Only run after
         the lifecycleBeanProcessor has run: -->
    <!--  
    5. 启用 IOC 容器中使用 shiro 的注解. 但必须在配置了 LifecycleBeanPostProcessor 之后才可以使用. 
    -->     
    <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
          depends-on="lifecycleBeanPostProcessor"/>
    <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
        <property name="securityManager" ref="securityManager"/>
    </bean>

    <!-- Define the Shiro Filter here (as a FactoryBean) instead of directly in web.xml -
         web.xml uses the DelegatingFilterProxy to access this bean.  This allows us
         to wire things with more control as well utilize nice Spring things such as
         PropertiesPlaceholderConfigurer and abstract beans or anything else we might need: -->
    <!--  
    6. 配置 ShiroFilter. 
    6.1 id 必须和 web.xml 文件中配置的 DelegatingFilterProxy 的 <filter-name> 一致.
                      若不一致, 则会抛出: NoSuchBeanDefinitionException. 因为 Shiro 会来 IOC 容器中查找和 <filter-name> 名字对应的 filter bean.
    -->     
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager"/>
        <property name="loginUrl" value="/login.jsp"/>
        <property name="successUrl" value="/list.jsp"/>
        <property name="unauthorizedUrl" value="/unauthorized.jsp"/>
        
        <property name="filterChainDefinitionMap" ref="filterChainDefinitionMap"></property>
        
        <!--  
        	配置哪些页面需要受保护. 
        	以及访问这些页面需要的权限. 
        	1). anon 可以被匿名访问
        	2). authc 必须认证(即登录)后才可能访问的页面. 
        	3). logout 登出.
        	4). roles 角色过滤器
        -->
        <!--  
        <property name="filterChainDefinitions">
            <value>
                /login.jsp = anon
                /shiro/login = anon
                /shiro/logout = logout
                
                /user.jsp = roles[user]
                /admin.jsp = roles[admin]
                
                # everything else requires authentication:
                /** = authc
            </value>
        </property>
        -->
    </bean>
    
    <!-- 配置一个 bean, 该 bean 实际上是一个 Map. 通过实例工厂方法的方式 -->
    <bean id="filterChainDefinitionMap" 
    	factory-bean="filterChainDefinitionMapBuilder" factory-method="buildFilterChainDefinitionMap"></bean>
    
    <bean id="filterChainDefinitionMapBuilder"
    	class="com.atguigu.shiro.factory.FilterChainDefinitionMapBuilder"></bean>
    
    <bean id="shiroService"
    	class="com.atguigu.shiro.services.ShiroService"></bean>

</beans>

```

1.url权限采取第一次匹配优先的方式





shiro认证思路

1.获取当前的Subject. 

```java
Subject currentUser = SecurityUtils.getSubject();
```

2.测试当前的用户是否已经被认证，即是否已经登录。

```java 
currentUser.isAuthenticated()
```

3.若没有被认证，则把用户名和密码封装为UsernamePasswordToken对象

```java
UsernamePasswordToken token = new UsernamePasswordToken(username, password);
```

4.执行登陆

```java
 currentUser.login(token);
```

5.自定义Realm的方法，从数据库中获取对应的记录，返回给Shiro. 

1).需要继承org.apache.shiro.realm.AuthorizingRealm;

2).需要实现doGetAuthenticationInfo(AuthenticationToken token)方法

6.由shiro完成密码的比对。

```
密码的比对:
通过 AuthenticatingRealm 的 credentialsMatcher 属性来进行的密码的比对!
```



如何做到md5盐值加密：
1). 在 doGetAuthenticationInfo 方法返回值创建 SimpleAuthenticationInfo 对象的时候, 需要使用SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName) 构造器
2). 使用 ByteSource.Util.bytes() 来计算盐值. 
3). 盐值需要唯一: 一般使用随机字符串或 user id
4). 使用 new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations); 来计算盐值加密后的密码的值. 



授权流程分析

1.授权需要继承AuthorizingRealm类，并实现其doGetAuthorizationInfo方法。

2.AuthorizingRealm类继承自AuthenticatingRealm，但没有实现AuthenticatingRealm中的doGetAuthenticationInfo， 所以认证授权只需要继承AuthorizingRealm，同时实现AuthorizingRealm的两个方法



Shiro权限注解

@RequiresAuthentication：表示当前Subject已经通过login进行了身份验证；即Subject.isAuthenticated()返回true。

@RequiresUser：表示当前Subject已经身份验证或者通过记住我登录的。

@RequiresGuest：表示当前Subject没有身份验证或通过记住我登录过，即是游客身份。

@RequeriesRoles(value={"admin", "user"}, logical=Logical.AND)：表示当前Subject需要角色admin和user

@RequiresPermissions(value = {"user:a", "user:b"}, logical=Logical.OR)：表示当前Subject需要权限user:a或user:b