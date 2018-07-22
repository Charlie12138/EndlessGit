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

