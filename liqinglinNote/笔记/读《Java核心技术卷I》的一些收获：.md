# 读《Java核心技术卷I》的一些收获：

:one:仅当类没有提供任何构造器的时候，系统才会提供一个默认的构造器。如果在编写的时候写了一个构造器，哪怕是很简单的，想要这个类的用户能够采用下列方式构造实例：

```java
new ClassName()
```

就必须提供一个默认的无参构造器

```java
//所有域都会被赋予默认值，数值类型设置为0、布尔类型数据设置为false、所有对象变量将设置为null
public ClassName() {
}
```

**与spring的一些联系：spring的IOC容器在初始化bean类实例的时候需要bean类提供无参构造器（如果bean类没有构造器，系统则会提供一个默认无参构造器），否则如果在编写的时候写了一个构造器，哪怕是很简单的，只要有参构造器，却没有编写无参构造器，就会报错**

```java
org.apache.ibatis.executor.ExecutorException: No constructor found in com.example.springbootmybatisdemo.bean.Employee matching [java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer]
	at 
	·······
```

:two:在实体类中构造器中可以调用另一个构造器，这样对公共的构造器代码部分编写一次即可

```java
public Employee(Double s) {
    this("Employee #" + nextId, s);
    ······
}
当调用Employee(60000)时， Employee(double)构造器将调用Employee(String, double)构造器
```

