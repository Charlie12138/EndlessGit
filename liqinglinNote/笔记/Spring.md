# Spring  

##### :one:spring框架可以管理web层，业务层，dao层，持久层，该spring可以配置各个组件（bean)，并且维护各个bean之间的关系

------



##### :two:

![:two:](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/9afda46be60022ef7f00882e0cb733f.png)

* spring实际上是一个容器框架，可以配置各种bean(action/service/domain/dao)，并且可以维护bean与bean的关系，当我们需要使用某个bean的时候，我们可以getBean(id)，使用。

* ioc：ioc(inverse of controll)控制反转：所谓控制反转就是把创建对象（bean）,和维护对象（bean）的关系的权利从程序中转移到spring的容器（applicationContext.xml）,而程序本身不再维护。

* di：di(dependency injection)依赖注入：实际上di和ioc是同一个概念，spring设计者认为di更准确表示spring核心技术。

* 学习框架，最重要就是学习各个配置。

  ------

  

##### :three:**从ApplicationContext应用上下文容器中获取bean和从bean工厂容器中获取bean**

* 从ApplicationContext应用上下文容器中获取bean，当我们去实例化applicationContext.xml，该文件中配置的bean被实例（该bean scope 时singleton）

  ```
  ApplicationContext ac = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
  ```

* （已经废弃）从bean工厂容器中获取bean, 容器bean不会被实例化，只有当你使用getBean某个bean时，才会实时创建

  ```
  BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("META-INF/applicationContext.xml"));
  beanFactory.getBean("student");
  ```

  #### <bean scope="singleton|prototype|request|session/>

|         范围         |                    /                    |
| :------------------: | :-------------------------------------: |
| singleton（Default） |                  单例                   |
|      prototype       |     原型：每次创建的bean对象不一样      |
|       request        |    一次请求中有效（java web开发中）     |
|       session        |     session级有效（java web开发中）     |
|    global-session    | 在web中spring容器ApplicationContext一致 |

* ClassPathXmlApplicationContext:从类路径中加载

  FileSystemXmlApplicationContext:从文件系统加载

  XmlWebApplicationContext:从web系统中加载

------



##### :four:Bean的生命周期：

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180723003057.png)

------



##### :five: 装配Bean

1.List

```
<property name="list">
    <list>
        <value>xxx</value>
    </list>
</property>
```

2. Set

```
<property name="set">
    <set>
        <value>xxx</value>
    </set>
</property>

<property name="set">
    <set>
        <ref bean="xxx"/>
    </set>
</property>
```

3.Map

```
<property name="map">
    <map>
        <entry key="xxx" value-ref="emp1"/>
    </map>
</property>
```

4.内部Bean

```
<bean id="xxx" class="xxx">
     <property name="xxx">
         <!--1-->
         <ref bean="bean名"/>
         <!--2-->
         <bean>
              <property>
              </property>
          </bean>
     </property>
</bean>
```

5.继承

```
<bean id="student" class="com.lql.inherit.Student">
    <property name="name" value="qinglin"/>
    <property name="age" value="19"/> 
 </bean>
 <bean id="gradate" parent="student" class="com.lql.inherit.Gradate">
 <!--如果子类bean配置父类属性，则会覆盖父类的属性-->
     <property name="gradate" value="本科生"/>
 </bean>
```

------

6.通过构造函数注入依赖

```
<bean>
    <constructor-arg index="i" type="xxx" value="">
    ......
 </bean>
```

:artificial_satellite:   set注入的缺点是无法清晰表达哪些属性是必须的，哪些是可选的，构造注入的优势是**通过构造强制依赖关系**，不可能实例化不完全的或无法使用的bean

**设值注入：**

a)与传统的Javabean的写法更相似，通过setter方法设定依赖关系显得更加直观自然

b)对于复杂的依赖关系，如果采用构造注入，会导致构造器过于臃肿

c)多参数情况下使得构造器变得更加笨重

**构造注入：**

a)构造注入可以在构造器中决定依赖关系的注入顺序

b)对于依赖关系无须变化的bean，构造注入更有用处，无须担心后续代码对依赖关系的破坏

c)依赖关系只能在构造器中设定，更符合高内聚的原则

*建议采用以设值注入为主，构造注入为辅的注入策略。对于依赖关系无须变化的注入，尽量采用构造注入；而其它依赖关系的注入，则考虑设置注入。*

------

7.bean自动装配

| 模式                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| no                                                           | 这是默认的设置，它意味着没有自动装配，你应该使用显式的bean引用来连线。你不用为了连线做特殊的事。在依赖注入章节你已经看到这个了。 |
| [byName](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byname.html) | 由属性名自动装配。Spring 容器看到在 XML 配置文件中 bean 的*自动装配*的属性设置为 *byName*。然后尝试匹配，并且将它的属性与在配置文件中被定义为**相同名称**的 beans 的属性进行连接。 |
| [byType](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byType.html) | 由属性数据类型自动装配。Spring 容器看到在 XML 配置文件中 bean 的*自动装配*的属性设置为 *byType*。然后如果它的**类型**匹配配置文件中的一个确切的 bean 名称，它将尝试匹配和连接属性的类型。如果存在不止一个这样的 bean或者没有这样的bean，则一个致命的异常将会被抛出。 |
| [constructor](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-by-Constructor.html) | 类似于 byType，但该类型适用于构造函数参数类型。如果在容器中没有一个构造函数参数类型的 bean，则一个致命错误将会发生。 |
| autodetect                                                   | Spring首先尝试通过 *constructor* 使用自动装配来连接，如果它不执行，Spring 尝试通过 *byType* 来自动装配。 |
| default                                                      | 这个需要在<beans default-autowire="指定"/>当你在<beans>指定了default-autowire后，所有的bean的默认autowire就是指定的装配方法，如果没有在<beans default-autowire="指定"/>，则default-autowire="no" |

------

8.通过**<context:property-placeholder location="properties文件地址 ，多个属性文件地址"/>**引入属性文件。

使用spring的特殊bean：运行在spring容器中的bean不知道自己的注册名，运行在哪里。实现以下三个接口：

BeanNameAware:知道自己的名字

BeanFactoryAware:所处的bean工厂

ApplicationContextAware:所在上下文

##### :six:AOP

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180723221124.png)

```

<bean id="userImp" class="com.lql.aop2.UserServiceImp"/>

<bean id="log" class="com.lql.aop2.Log"/>

<aop:config>
     <aop:aspect ref="log">
          <aop:pointcut id="pointcut" expression="execution(* com.lql.aop2.*.*(..))"/>
          前置方法
          <aop:before method="before" pointcut-ref="pointcut"/>
          后置方法
          <aop:after method="after" pointcut-ref="pointcut"/>
     </aop:aspect>
</aop:config>

```

```
接口
public interface UserService {
	public void sayHello();
}

```

```
实现类
public class UserServiceImp implements UserService {
	@Override
	public void sayHello() {
		System.out.println("hello, hello!");
	}
}
```

```
Log内放有前置和后置方法，不需要继承或实现什么
public class Log {
	public void before (){
		System.out.println("前置方法！");
	}

	public void after(){
		System.out.println("后置方法！");
	}
}
```

**注解方式**

```
beans.xml配置：
```

```
通知类
@Aspect
public class Log {
	@Before("execution(* com.lql.aop2.*.*(..))")
	public void before (){
		System.out.println("前置方法！");
	}
	@After("execution(* com.lql.aop2.*.*(..))")
	public void after(){
		System.out.println("后置方法！");
	}

	@Around("execution(* com.lql.aop2.*.*(..))")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("环绕前");
		System.out.println(proceedingJoinPoint.getSignature());
		Object object = proceedingJoinPoint.proceed();
		System.out.println("环绕后");
		return object;
	}
}
```

------



##### :seven:mybatis+maven+spring 

1.配置文件

```
beans.xml

    <!--配置数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/db_book"/>
        <property name="username" value="root"/>
        <property name="password" value="my159357@sql"/>
    </bean>
    <!--配置sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:conf.xml"/>
    </bean>

    <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

    <bean id="userDao" class="com.lql.DaoImp.UserDaoImp">
        <property name="sqlSession" ref="sqlSessionTemplate"/>
    </bean>
```

```
mybatis的配置
 <typeAliases>
    <!--userMapper.xml中直接写实体类名-->
    <package name="com.lql.PO"/>
    </typeAliases>
 <mappers>
    <mapper resource="Mapper/userMapper.xml"/>
 </mappers>
```

```
测试Test
public class App 
{
    public static void main( String[] args ) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/beans.xml");
        UserDao userDao = (UserDao) ac.getBean("userDao");
        System.out.println(userDao.selectUser().size());
    }
}

```

------



##### :eight:声明式事务

1.什么事务：

```
事务必须服从ACID原则。ACID指的是原子性（atomicity）、一致性（consistency）、隔离性（isolation）和持久性（durability）。
通俗理解，事务其实就是一系列指令的集合。
```

- 原子性：操作这些指令时，要么全部执行成功，要么全部不执行。只要其中一个指令执行失败，所有的指令都执行失败，数据进行回滚，回到执行指令前的数据状态。
- 一致性：事务的执行使数据从一个状态转换为另一个状态，但是对于整个数据的完整性保持稳定。
- 隔离性：在该事务执行的过程中，无论发生的任何数据的改变都应该只存在于该事务之中，对外界不存在任何影响。只有在事务确定正确提交之后，才会显示该事务对数据的改变。其他事务才能获取到这些改变后的数据。
- 持久性：当事务正确完成后，它对于数据的改变是永久性的。

2.spring的声明式事务：

​	DAO层的事务控制，通常是指对于数据库访问操作的事务处理，普通的实现方式即使用代码来控制事务的提交(commit)、回滚(rollback)等操作，这些代码若不使用框架进行整合，会造成DAO层和业务逻辑层的高耦合。由此spring提供了声明式事务的方案，即用声明式的事务代替代码式的事务。事务控制使用注解和配置文件声明等方式实现，其基本原理是使用AOP进行方法级别的事务控制，当然你也可以利用spring的规范自定义自己的代码式事务并使用AOP绑定自定义的声明式事务。

```
DataSourceTransactionManager 单一数据源事务管理器，依赖于你的某一个datasource，提供对单个DataSource的事务管理。
```

```
PROPAGATION_REQUIRED--支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。

PROPAGATION_SUPPORTS--支持当前事务，如果当前没有事务，就以非事务方式执行。

PROPAGATION_MANDATORY--支持当前事务，如果当前没有事务，就抛出异常。

PROPAGATION_REQUIRES_NEW--新建事务，如果当前存在事务，把当前事务挂起。

PROPAGATION_NOT_SUPPORTED--以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。

PROPAGATION_NEVER--以非事务方式执行，如果当前存在事务，则抛出异常。

PROPAGATION_NESTED--如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。
```

```
beans.xml配置

<!--配置事务通知-->
    <tx:advice id="txAdvice" transaction-manager="txManager">
        <tx:attributes>
            <!--配置那些方法使用什么样的事务，配置事务的传播特性-->
            <tx:method name="selectUser" propagation="REQUIRED"/>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="insert" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="remove*" propagation="REQUIRED"/>
            <tx:method name="add" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
    <aop:config>
        <aop:pointcut expression="execution(* com.lql.DaoImp.*.*(..))" id="pointcut"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut"/>
    </aop:config>
    <!-- 声明式事务配置 结束 -->
```

------



##### :nine:注解配mybatis

```
com.lql.Dao.UserMapper
public interface UserMapper {
	@Select(" select * from user")
	public List<User> selectUser();
}
```

```
com.lql.Service.UserService
public interface UserService {
	public List<User> selectUser();
}
```

```
com.lql.ServiceImp.UserServiceImp
public class UserServiceImp implements UserService {
	private UserMapper userMapper = null;
	public UserMapper getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Override
	public List<User> selectUser() {
		return userMapper.selectUser();
	}
}
```

```
beans.xml
	<bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
        <property name="mapperInterface" value="com.lql.Dao.UserMapper"/>
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>

    <bean id="userService" class="com.lql.ServiceImp.UserServiceImp">
        <property name="userMapper" ref="userMapper"/>
   	</bean>
```

