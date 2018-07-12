# MAVEN

## 1、mevan介绍以及环境搭建

### 1.1、概念

Maven是基于项目对象模型(POM),可以通过一小段描述信息来管理项目的构建、报告和文档的软件项目管理工具。

### 1.2、maven的安装目录

*  boot目录包含一个类加载器的框架。
*  bin目录主要是一些运行的脚本。
*  conf是配置文件目录。
*  lib包含一些需要用到的类库

### 1.3、配置环境变量

* M2_HOME 值为maven的安装目录
* PATH的值 D:\apache-maven-3.2.1\bin

## 2、Maven的文件结构和第一个HelloWorld

### 2.1、文件结构

```
-src
	-main
		-java
			-package
	-test
		-java
			-package
	-resources
```

### 2.2、poe.xml的配置

```xml
<?xml version="1.0" encoding="utf-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apche.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.imooc.maven01</groupId>
	<artifactId>maven01-model</artifactId>
	<version>0.0.1-SNAPSHOT</version>
    
<dependencies>
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.10</version>
	</dependency>
</dependencies>

</project>
```

* 其中groupId的值就是包名

### 2.3、几个简单的命令

* mvn compile 编译
* mvn test 运行测试
* mvn package 项目打包

## 3、maven的常见构建命令

```
mvn  -v 查看maven版本
	 compile 编译
	 test 测试
 	 package 打包

clean 删除target
install 安装jar包到本地仓库
```

## 4、maven自动建立目录骨架

```
mvn archetype:generate 按照提示进行选择
mvn archetype:generate -DgroupId=组织名，公司网址的反写+项目名字 
	-DartifactId=项目名-模块名
	-Dversion=版本号
	-Dpackage=代码所踩在的包名

* -DarchetypeCatalog=internal 解决卡顿的问题
```

## 5、maven中的坐标和仓库

```
坐标：构件
仓库：
	中央仓库：http://repo.maven.apache.org/maven2
	本地仓库：
更改仓库地址：<localRepository>D:\myFile\maven\repos</localRepository>
```

## 6、maven的生命周期和插件

### 6.1、完整的项目构建过程包括：

清理、编译、测试、打包、集成测试、验证、部署

### 6.2、maven生命周期：

```
clean 清理项目
	pre-clean 执行清理前的工作
	clean 清理上一次构建生成的所有文件
	post-clean 执行清理后的文件
default 构建项目（最核心）
	compile test pack install
site 生成项目站点
	pre-site 在生成项目站点前要完成的工作
	site 生成项目的站点文档
	post-site 在生成项目站点后要完成的工作
	site-deploy 发布生成的站点到服务器上

```

```xml
<build>
    <plugins>
        <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-source-plugin</artifactId>
              <version>2.4</version>
              <executions>
                <execution>
                  <phase>package</phase>
                  <goals>
                    <goal>jar-no-fork</goal>
                  </goals>
                </execution>
              </executions>
           </plugin>
       </plugins>
</build>
```

## 7、maven中Pom.xml解析



```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  		<!--指定了当前的pom版本-->
  <modelVersion>4.0.0</modelVersion>
  <groupId>反写的公司网址+项目名</groupId>
  <artifactId>项目名+模块名</artifactId>
  <!-- 第一个0 表示大版本号
	第二个0 表示分支版本号
	第三个0 表示小版本号
	0.0.1
	snapshot快照
	alpha 内部测试
	beta 公测
	Release 稳定
	GA 正式发布
-->
    <version></version>
  	<!--默认是jar
	-->
    <packaging></packaging>
    <!--项目描述名-->
    <name></name>
    <!--项目地址-->
    <url></url>
    <!--项目描述-->
    <description></description>
	<developers></developers>
	<licenses></licenses>
	<organization></organization>

    <dependencies>
    	<dependenncy>
        	<groupId></groupId>
            <artifactId></artifactId>
            <version></version>
            <type></type>
            <scope></scope>
            <!--设置依赖是否可选-->
            <optional></optional>
            <!--排除依赖传递列表-->
            <exclusions>
            	<exclusion></exclusion>
            </exclusions>
        </dependenncy>
    </dependencies>
   		
    	<!--依赖的管理-->
    <dependencyManagement>
        <dependencies>
            <dependency></dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <!--插件列表-->
        <plugins>
            <plugin>
                <groupId></groupId>
                <artifactId></artifactId>
                <version></version>
            </plugin>
        </plugins>
    </build>  
    
</project>
```

## 8、maven的依赖范围

* compile：默认的范围，编译测试运行都有效
* provided：在编译和测试时有效
* runtime：在测试和运行时有效
* test：只在测试时有效
* system：与本机系统相关联，可移植性差
* import：导入的范围，它只使用在dependencyManagement中，表示从其它的pom中导入dependency的配置

## 9、maven的依赖传递

## 10、maven的依赖冲突

1. 短路优先

2. 先声明先优先

   如果路径长度相同，则谁先声明，先解析谁

## 11、maven的聚合和继承

* 聚合

```xml
<modules>
        <module>../hongxingbige</module>
        <module>../hongxing-nage</module>
        <module>../hongxing-shanji</module>
</modules>
```

* 继承

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <junit.version>4.10</junit.version>
</properties>

<dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
      </dependency>
    </dependencies>
</dependencyManagement>
```

```xml
<parent>
    <groupId>hongxing</groupId>
    <artifactId>hongxing-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
</parent>
```

## 12、使用maven构建web项目

1. 打开IDEA，创建新的项目
2. 选择Maven，以及自己电脑的的jdk
3. 接下来自定义GroupId以及ArtifactId
4. 然后自定义项目名字，Finish
5. maven中，第一次创建会在src下生成main/java以及main/resources目录，src中的test目录是要自己创建的，test目录的中代码不会被发布，用于测试。
6. 接下来 maven中创建web项目

![Add Framework Support](https://github.com/Rzihan/JavaStudy/blob/master/image/maven/addFrameworkSupport.png?raw=true)

7. 右键Add  Framework Support，出现项目创建选项：

![web project](https://github.com/Rzihan/JavaStudy/blob/master/image/maven/webProject.png?raw=true)

8. 选择web项目，点击确定，之后先配置好pom.xml下的web项目依赖包

![pomDependence](https://github.com/Rzihan/JavaStudy/blob/master/image/maven/pomDependencies.png?raw=true)

9. idea出现提示Maven projects need to be imported，点击Import Changes Enable Auto-Import，让maven自动下载导抱。

   查看项目下的External Libraries，可以发现依赖包已经下载好了：

![external Libraries](https://github.com/Rzihan/JavaStudy/blob/master/image/maven/externalLibraries.png?raw=true)

10. 接下来在src/main/java目录下新建一个servlet；
11. 最后配置tomcat服务器

![](https://github.com/Rzihan/JavaStudy/blob/master/image/maven/tomcat.png?raw=true)

![](https://github.com/Rzihan/JavaStudy/blob/master/image/maven/tomcat2.png?raw=true)

12. 最后发布运行，然后再浏览器上输入servlet请求。