#    					Shiro

* 1.Apache Shio是一个Java的安全（权限）框架。
* Shiro可以非常容易的开发出足够好的应用，可以应用在JavaSE和JavaEE环境
* Shiro可以完成：认证、授权、加密、会话管理、、与Web集成、缓存等。

 ## Shiro实例

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