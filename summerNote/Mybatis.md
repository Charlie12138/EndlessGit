# Mybatis

### 1.IDEA搭建第一个mybatis工程:first_quarter_moon:

* 创建一个maven项目

* 配置pom.xml

  ```
   <!-- mybatis核心包 -->
           <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis</artifactId>
              <version>3.3.0</version>
          </dependency>
          <!-- mysql驱动包 -->
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.29</version>
          </dependency>
          <!-- junit测试包 -->
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.11</version>
              <scope>test</scope>
          </dependency>
          <!-- 日志文件管理包 -->
          <dependency>
              <groupId>log4j</groupId>
              <artifactId>log4j</artifactId>
              <version>1.2.17</version>
          </dependency>
          <dependency>
              <groupId>org.slf4j</groupId>
              <artifactId>slf4j-api</artifactId>
              <version>1.7.12</version>
          </dependency>
          <dependency>
              <groupId>org.slf4j</groupId>
              <artifactId>slf4j-log4j12</artifactId>
              <version>1.7.12</version>
          </dependency>
      </dependencies>
  ```

* 在src/java下新建一个包com.lql.pojo,然后在该包下创建一个java文件，文件名User.

* 在src/main/resources下创建一个文件夹mapper，在该文件夹下创建一个UserMapper.xml文件

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.hgc.pojo.User">
    <select id="findById" parameterType="int" resultType="com.hgc.pojo.User">
        SELECT * FROM User WHERE id=#{id}
    </select>
</mapper>
```

* 在src/main/resources下创建mysql.properties文件,代码如下 

```
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost/mybatis 
jdbc.username=root 
jdbc.password=My159357@sql 
```

* 在src/main/resources下创建mybatis-config.xml文件,代码如下

  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <properties resource="mysql.properties"/>
      <settings>
          <!--全局性设置懒加载。如果设为‘false’，则所有相关联的都会被初始化加载,默认值为false-->
          <setting name="lazyLoadingEnabled" value="true"/>
          <!--当设置为‘true’的时候，懒加载的对象可能被任何懒属性全部加载。否则，每个属性都按需加载。默认值为true-->
          <setting name="aggressiveLazyLoading" value="false"/>
      </settings>
      <typeAliases>
          <!-- 其实就是将bean的替换成一个短的名字-->
          <typeAlias type="com.hgc.pojo.User" alias="User"/>
      </typeAliases>
      <!--对事务的管理和连接池的配置-->
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"></transactionManager>
              <dataSource type="POOLED"><!--POOLED：使用Mybatis自带的数据库连接池来管理数据库连接-->
                  <property name="driver" value="${jdbc.driver}"/>
                  <property name="url" value="${jdbc.url}"/>
                  <property name="username" value="${jdbc.username}"/>
                  <property name="password" value="${jdbc.password}"/>
              </dataSource>
          </environment>
      </environments>
      <!--mapping文件路径配置-->
      <mappers>
          <mapper resource="mapper/UserMapper.xml"/>
      </mappers>
  
  </configuration>
  ```

* 建立数据库

  ```
  CREATE TABLE `User` ( 
  `id` int(11) NOT NULL AUTO_INCREMENT, 
  `name` varchar(255) DEFAULT NULL, 
  `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) 
  ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8; 
  INSERT INTO `User` VALUES (1, 'test', 18); 
  INSERT INTO `User` VALUES (2, '张三', 25); 
  ```

* 在src/main/java下创建一个包com.hgc.test，在该包下创建UserTest.java文件，代码如下 

```
public class UserTest {
	public static void main(String[] args) { 
		String resource ="mybatis-config.xml"; 
		Reader reader = null; 
		try { 
			reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 			SqlSession session = sqlSessionFactory.openSession(); 
			User user = session.selectOne("findById",2); 
			session.commit(); 
			System.out.println(user.getName()); 
		}catch (IOException e){
        	e.printStackTrace(); 	        	
       	} 
    } 
} 
```

* 运行
* [项目源码](https://github.com/Charlie12138/EndlessGit/tree/master/protectProject/Test)



### 2.在maven下运行mybatis遇到的问题：🌓 

*  要将xml文件放到resources文件夹里:cold_sweat:

在eclipse中，把资源文件放在src文件夹下，是可以找到的；

但是在idea中，直接把资源文件放在src文件夹下，如果不进行设置，是不能被找到的。



* **解决方法** :happy:

1.将所有资源文件放在resources文件夹下，这样做很方便，比较容易想到，但是层次性就很差了，比如mybatis的映射配置文件mapper.xml，本来需要放在特定的包里面，与dao层，service层等层次为同一个层级，如今只能放在resource文件夹下，不方便分层；

2.如果该项目是maven项目：

配置maven的pom文件配置，在pom文件中找到<build>节点，添加下列代码：

```
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                 <include>**/*.xml</include>
               </includes>
            </resource>
        </resources>
    </build>
```





### 3.对User表进行CRUD操作：🌓 

#### xml实现:palm_tree:

* 配置sql映射xml文件userMapper.xml

  ```
  <?xml version="1.0" encoding="utf-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="userMapper">
      <!--CRUD操作-->
  
      <!--增-->
      <insert id="addUser" parameterType="User">
          insert into User(name, age) values (#{name}, #{age});
      </insert>
  
      <!--删-->
      <delete id="deleteUser" parameterType="int">
          delete from User where id = #{id}
      </delete>
  
      <!--查-->
      <select id="findById" parameterType="int" resultType="User">
          SELECT *FROM User WHERE id=#{id}
      </select>
  
      <select id="getAll" resultType="User">
          SELECT *from User;
      </select>
  
      <!--改-->
      <update id="updateUser" parameterType="User">
          update User set name = #{name}, age = #{age} where id = #{id}
      </update>
  </mapper>
  ```

  

* 在conf.xml注册上面的映射文件

  ```
  	<mappers>
          <mapper resource="userMapper.xml"/>
      </mappers>
  ```

  

* 实现

  ```
  public  void addUser(){
  		String resource = "conf.xml";
  		InputStream is = 			MybatisUtil.class.getClassLoader().getResourceAsStream(resource);//类加载器
  		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
  		SqlSession session = factory.openSession(true);
  		String statement = "userMapper.addUser";
  		int ins = session.insert(statement, new User(-1, "sss", 26));
  		session.close();
  		System.out.println(ins);
  	}
  ......
  ```



#### 注解实现🌴 

* 建立一个接口类UserMapper:

  ```
  public interface UserMapper {
  	@Insert("insert into User(name, age) values (#{name}, #{age})")
  	public int add(User user);
  }
  ```

  

* 实现

  ```
  public  void addUser2(){
  		String resource = "conf.xml";
  		InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
  		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
  		SqlSession session = factory.openSession(true);
  		String statement = "userMapper.addUser";
  		UserMapper mapper = session.getMapper(UserMapper.class);
  		int ins = mapper.add(new User(-1, "lql", 16));
  		session.close();
  		System.out.println(ins);
  	}
  ```

  

### 4.对xml方式的优化🌓 

* 连接数据库的配置单独放在一个properties文件中

  ```
  driver = com.mysql.jdbc.Driver
  url = jdbc:mysql://localhost:3306/mybatis
  name = root
  password =my159357@sql
  ```

* conf.xml优化

  ```
  		 <dataSource type="POOLED">
                  <property name="driver" value="${driver}"/>
                  <property name="url" value="${url}"/>
                  <property name="username" value="${name}"/>
                  <property name="password" value="${password}"/>
            </dataSource>
  ```

  

### 5.为实体类定义别名,简化sql映射xml文件中的引用🌓 

* 在conf.xml设置

  ```
   <!--配置实体类别名-->
      <typeAliases>
          <!--<typeAlias type="com.lql.pojo.User" alias="_User"/>-->
          <package name="com.lql.pojo"/><!--userMapper.xml中直接写实体类名-->
      </typeAliases>
  ```

  

### 6.在控制台输出日志信息🌓

* 配置log4j.xml

  ```
  <?xml version="1.0"  encoding="UTF-8" ?>
  <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
  <log4j:configuration>
      <appender name="myConsole" class="org.apache.log4j.ConsoleAppender">
          <layout class="org.apache.log4j.PatternLayout">
              <param name="ConversionPattern"
                     value="%-d{yyyy-MM-dd HH:mm:ss,SSS} [%c]-[%p] %m%n"/>
          </layout>
      </appender>
      <root>
          <priority value="debug"/>
          <appender-ref ref="myConsole"/>
      </root>
  </log4j:configuration>
  ```

  

### 7.实体类字段名与数据库字段名不同会有冲突🌓

**方式一: 通过在sql语句中定义别名**🌴

```
<select id="selectOrder" parameterType="int" resultType="_Order">
	select order_id id, order_no orderNo,order_price price from orders where order_id=#{id}
</select>
```

​		

**方式二: 通过<resultMap>**🌴

```
<select id="selectOrderResultMap" parameterType="int" resultMap="orderResultMap">
	select * from orders where order_id=#{id}
</select>
<resultMap type="Order" id="orderResultMap">
	<id property="id" column="order_id"/>
	<result property="orderNo" column="order_no"/>
	<result property="price" column="order_price"/>
</resultMap>
```



### 8.实现关联表查询

:one:**一对一关联**

* ```
  <!-- 
  方式一：嵌套结果：使用嵌套结果映射来处理重复的联合结果的子集
           封装联表查询的数据(去除重复的数据)
  select * from class c, teacher t where c.teacher_id=t.t_id and  c.c_id=1
  -->
  
  <select id="getClass" parameterType="int" resultMap="ClassResultMap">
  	select * from class c, teacher t where c.teacher_id=t.t_id and  c.c_id=#{id}
  </select>
  
  <resultMap type="Classes" id="ClassResultMap">
  
  	<id property="id" column="c_id"/>
  	<result property="name" column="c_name"/>
  	<association property="teacher" javaType="Teacher">
  		<id property="id" column="t_id"/>
  		<result property="name" column="t_name"/>
  	</association>
  	
  </resultMap>
  
  <!-- 
  方式二：嵌套查询：通过执行另外一个SQL映射语句来返回预期的复杂类型
  	SELECT * FROM class WHERE c_id=1;
  	SELECT * FROM teacher WHERE t_id=1   //1 是上一个查询得到的teacher_id的值
  -->
  
   <select id="getClass2" parameterType="int" resultMap="ClassResultMap2">
  	select * from class where c_id=#{id}
   </select>
   
   <resultMap type="Classes" id="ClassResultMap2">
   
  	<id property="id" column="c_id"/>
  	<result property="name" column="c_name"/>
  	<association property="teacher" column="teacher_id" select="getTeacher"/>
  
   </resultMap>
   
   <select id="getTeacher" parameterType="int" resultType="_Teacher">
  	SELECT t_id id, t_name name FROM teacher WHERE t_id=#{id}
   </select>
  ```

  



























