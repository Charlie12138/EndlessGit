# Class.ForName

### 1.在java.lang.Class中，有两个重载的forName方法，分别是：

1.  static Class <?> forName(String className)，该方法等价于Class.forName(className, true, this.getClass().getClassLoader())
2. static Class<?> forName(String className, boolean initialize,ClassLoader loader)，其中3个参数分别表示：className - 所需类的完全限定名，initialize - 是否必须初始化类，loader - 用于加载类的类加载器。

   **forName方法的作用就是：**
   使用给定的**类加载器**，返回与带有给定字符串名的类或接口相关联的`Class` 对象。给定一个**类或接口的完全限定名**，此方法会试图**定位、加载和链接该类或接口**。指定的类加载器用于加载该类或接口，如果参数`loader` 为 null，则该类通过**引导类加载器加载**。只有 `initialize` 参数为 `true`且以前未被初始化时，才初始化该类。

------



### 2.JDBC中使用Class.forName("xxx")的意义

在Java开发特别是数据库开发中，经常会用到Class.forName( )这个方法。通过上面的介绍，已经了解了Class.forName()方法的作用就是为了**动态加载类**，并**决定是否需要初始化类的静态部分**，而在JDBC规范中明确要求**Driver(数据库驱动)类必须向DriverManager注册自己**。

* 在使用JDBC连接MySQL数据库时的时候有的仅仅需要**Class.forName("com.mysql.jdbc.Driver")——动态加载类并向DriverManager注册自己**；
* **Class.forName("com.mysql.jdbc.Driver").newInstance()——动态加载类并向DriverManager注册自己**。（没有必要，因为会生成Driver类的实例，而这个是我们没有用的，没有必要创建它。如果在Driver类中那个static块里面的部分写在了构造方法中，那么就必须使用Class.forName("com.mysql.jdbc.Driver").newInstance()来向DriverManager注册了。） 