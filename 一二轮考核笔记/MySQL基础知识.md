1.  
#### RDBMS 术语：
+ 数据库: 数据库是一些关联表的集合。
+ 数据表: 表是数据的矩阵。在一个数据库中的表看起来像一个简单的电子表格。
+ 列: 一列(数据元素) 包含了相同的数据, 例如邮政编码的数据。
+ 行：一行（=元组，或记录）是一组相关的数据，例如一条用户订阅的数据。
+ 冗余：存储两倍数据，冗余降低了性能，但提高了数据的安全性。
+ 主键：主键是唯一的。一个数据表中只能包含一个主键。你可以使用主键来查询数据。
+ 外键：外键用于关联两个表。
复合键：复合键（组合键）将多个列作为一个索引键，一般用于复合索引。
* 索引：使用索引可快速访问数据库表中的特定信息。索引是对数据库表中一列或多列的值进行排序的一种结构。类似于书籍的目录。
* 参照完整性: 参照的完整性要求关系中不允许引用不存在的实体。与实体完整性是关系模型必须满足的完整性约束条件，目的是保证数据的一致性。

2.
### mysql在管理员模式输入
```
net start mysql57启动
net stop mysql57关闭
```
3.M表示：数据的总长度（不包括小数点）
  D表示：小数位；
  eg：decimal（5，2）123.45
  存入数据的时候，按四舍五入计算。
  
  4.==dos建表基本操作示例==
  ```
  CREATE TABLE t_bookType(
	id int primary key auto_increment,
	bookTypeName varchar(20),
	bookTypeDesc varchar(200)
);//建表

CREATE TABLE t_book(
	id int primary key auto_increment,
	bookName varchar(20),
	author varchar(10),
	price decimal(6,2),
	bookTypeId int,
	constraint `fk` foreign key (`bookTypeId`)/*外键*/ references /*引用*/`t_bookType`(`id`)
);//建表+创建外键关联

desc t_bookType;//查看基本表结构

show create table t_bookType;// 查看表详细结构

alter table t_book rename t_book2;//修改表名

alter table t_book2 change bookName bookName2 varchar(20);//修改字段

alter table t_book2 add testField int after bookName2;//增加字段

alter table t_book2 drop testField;//删除字段

drop table t_book2;//删除表

drop table t_bookType;//删除表
```

### 模糊查询语句

* [link1](https://blog.csdn.net/livebecauseofyou/article/details/79267767)
* [link2](https://blog.csdn.net/qq_29933359/article/details/55095890)
* [link3](https://blog.csdn.net/c18772517102/article/details/74139823)
* 将模糊查询的结果保存在集合中：[link4](https://zhidao.baidu.com/question/93897031.html)



#### 主键、外键和索引的区别：

  || 主键|外键|索引
---|---|---|---
定义 | 一个表惟一的一条记录，不能重复，不允许为空|一个表中另一个表的主键，可以重复，可以是空值|该字段没有重复值，但可以有一个空值
作用 | 保证数据的完整性|与其他表建立联系|提高查询排序的速度
个数|只能有一个|可以有多个|有多个索引

##### ==如何设置主键==： 
* 主键应当是对用户没有意义的。如果用户看到了一个表示多对多关系的连接表中的数据，并抱怨它没有什么用处，那就证明它的主键设计地很好。

* 主键应该是单列的，以便提高连接和筛选操作的效率。
* 永远也不要更新主键。实际上，因为主键除了惟一地标识一行之外，再没有其他的用途了，所以也就没有理由去对它更新。如果主键需要更新，则说明主键应对用户无意义的原则被违反了。
* 主键不应包含动态变化的数据，如时间戳、创建时间列、修改时间列等。
* 主键应当有计算机自动生成。如果由人来对主键的创建进行干预，就会使它带有除了惟一标识一行以外的意义。一旦越过这个界限，就可能产生认为修改主键的动机，这样，这种系统用来链接记录行、管理记录行的关键手段就会落入不了解数据库设计的人的手中。


==今天对已存在的两个表中的一个表添加另一个表的主键为外键，遇到以下错误==：
```
sql 1452 Cannot add or update a child row:a foreign key constraint fails
```

==原因：==

**设置的外键和对应的另一个表的主键值不匹配。**

* 解决方法：

* 找出不匹配的值修改。

* 或者清空两表数据。






