# Spring  

##### :one:spring框架可以管理web层，业务层，dao层，持久层，该spring可以配置各个组件（bean)，并且维护各个bean之间的关系

##### :two:

![​:two:​](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/9afda46be60022ef7f00882e0cb733f.png)

* spring实际上是一个容器框架，可以配置各种bean(action/service/domain/dao)，并且可以维护bean与bean的关系，当我们需要使用某个bean的时候，我们可以getBean(id)，使用。
* ioc：ioc(inverse of controll)控制反转：所谓控制反转就是把创建对象（bean）,和维护对象（bean）的关系的权利从程序中转移到spring的容器（applicationContext.xml）,而程序本身不再维护。
* di：di(dependency injection)依赖注入：实际上di和ioc是同一个概念，spring设计者认为di更准确表示spring核心技术。
* 学习框架，最重要就是学习各个配置。

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

| singleton（Default） |                  单例                   |
| :------------------: | :-------------------------------------: |
|      prototype       |     原型：每次创建的bean对象不一样      |
|       request        |    一次请求中有效（java web开发中）     |
|       session        |     session级有效（java web开发中）     |
|    global-session    | 在web中spring容器ApplicationContext一致 |

* ClassPathXmlApplicationContext:从类路径中加载

  FileSystemXmlApplicationContext:从文件系统加载

  XmlWebApplicationContext:从web系统中加载

##### :four:Bean的生命周期：

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180723003057.png)

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

6.通过构造函数注入依赖

```
<bean>
    <constructor-arg index="i" type="xxx" value="">
    ......
 </bean>
```

set注入的缺点是无法清晰表达哪些属性是必须的，哪些是可选的，构造注入的优势是**通过构造强制依赖关系**，不可能实例化不完全的或无法使用的bean

7.自动装配

| 模式                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| no                                                           | 这是默认的设置，它意味着没有自动装配，你应该使用显式的bean引用来连线。你不用为了连线做特殊的事。在依赖注入章节你已经看到这个了。 |
| [byName](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byname.html) | 由属性名自动装配。Spring 容器看到在 XML 配置文件中 bean 的*自动装配*的属性设置为 *byName*。然后尝试匹配，并且将它的属性与在配置文件中被定义为**相同名称**的 beans 的属性进行连接。 |
| [byType](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byType.html) | 由属性数据类型自动装配。Spring 容器看到在 XML 配置文件中 bean 的*自动装配*的属性设置为 *byType*。然后如果它的**类型**匹配配置文件中的一个确切的 bean 名称，它将尝试匹配和连接属性的类型。如果存在不止一个这样的 bean或者没有这样的bean，则一个致命的异常将会被抛出。 |
| [constructor](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-by-Constructor.html) | 类似于 byType，但该类型适用于构造函数参数类型。如果在容器中没有一个构造函数参数类型的 bean，则一个致命错误将会发生。 |
| autodetect                                                   | Spring首先尝试通过 *constructor* 使用自动装配来连接，如果它不执行，Spring 尝试通过 *byType* 来自动装配。 |
| default                                                      | 这个需要在<beans default-autowire="指定"/>当你在<beans>指定了default-autowire后，所有的bean的默认autowire就是指定的装配方法，如果没有在<beans default-autowire="指定"/>，则default-autowire="no" |

8.通过**<context:property-placeholder location="properties文件地址 ，多个属性文件地址"/>**引入属性文件。

使用spring的特殊bean：运行在spring容器中的bean不知道自己的注册名，运行在哪里。实现以下三个接口：

BeanNameAware:知道自己的名字

BeanFactoryAware:所处的bean工厂

ApplicationContextAware:所在上下文