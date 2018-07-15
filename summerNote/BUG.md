# BUG

#### 1.jsp放入了Web-INF中出现404

#### 2.用IDEA创建maven框架下的mybatis项目的bug:

* 把src下的main/java删掉建了一个com.lql.test1文件夹，在运行时conf.xml里的

```
 	<mappers>
        <mapper resource="com/lql/test1userMapper.xml"/>
    </mappers>
```

​	resourse说找不到。

	##### 原因：com.lql.test1被作为根目录，编译后路径出现了问题

##### 解决：建一个java文件夹把com.lql.test1放进去

* 在实体类里没建无参的构造方法：

  ```
  Cause: org.apache.ibatis.executor.ExecutorException: No constructor found in com.lql.test1.User matching [java.lang.Integer, java.lang.String, java.lang.Integer]
  ```

  猜想大体上实例化流程就是，resultMap会去调用Bean的默认构造函数，然后将所有的成员变量和查询结果的列名形成映射，当你显示地指定了一个构造函数，然而它又不能将查询结果和显示指定的构造函数的参数形成映射的时候就会抛出异常

  ```
  No constructor found in com.tszhao.dao.User matching [java.lang.Integer, java.lang.String, java.lang.Integer]
  ```

  
  (它想找的是传入Integer的构造函数， 而你只有传入int的构造函数)所以要么不显式地指定构造函数，使用自动生成的默认构造函数要么在自己指定的构造函数中使用包装类型（当然也可以直接在你的那行构造函数上面显示地添加一个无参构造函数，不过看起来好像是多此一举）

