# maven

#### 1.准备工作

*   解压

* ​    配置环境变量

  > M2_HOME:安装路径
  >
  > path:%M2_HOME%\bin

* cmd: ==mvn -version==查看版本，是否成功配置环境变量

#### 视频例子

* 在maven-ch01中建立一个pom.xml文件，内容：

```
<?xml version="1.0" encoding="utf-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>zttc.itat.maven</groupId>
    <artifactId>maven-ch01</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</project>
```

* 继续创建文件夹：maven-ch01 -- >  src --> main （& test ） --> java；java 里写一个类，然后用命令行cd 到 maven-ch01，然后用使用 ==mvn compile==就会自动下载网上的包并将编写的类编译成class文件。

* 在test  下写一个测试类，在pom.xml里加入：

* ```
  <dependencies>
      <dependency>          //引用jar包
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.10</version>
      </dependency>
  </dependencies>
  ```

  继续在cmd中使用命令==mvn test==就会自动在网上下载测试类用到的jar包。

* ==mvn clean==命令：将mvn compile 和mvn test后创建的文件都删除。

* 将测试文件改出bug后执行mvn test，在maven-ch01\target\surfire-reports会有测试报告记录错误。

* ==mvn  package==命令：将项目打包成一个jar包。

#### 仓库

* 位置：自定义（方便管理，重装系统不会影响）
  * 我在D:/JDK8中建立maven/repository，在安装路径下**conf**找到settings.xml复制到新建的repository。两个**settings.xml**里的repository地址**都改为**新建的地址。
* 当输入mvn compile命令时就会在仓库中找到依赖的jar包，jar包存在就直接用，不存在再从网上下载。
* ==mvn install==命令：把jar包发到本地仓库中

#### 依赖范围

* ==test== 范围指的是测试范围有效，在编译和打包时都不会使用这个依赖
* ==complie==范围指的是编译范围有效，在编译和打包时都会将依赖存储进去
* ==provided==依赖：在编译和测试的过程有效，最后生成jar包时不会加入，诸如：servlet-api, 因为servlet-api, tomcat等web服务器已经存在了，如果再打包会冲突
* ==runtime==在运行的时候依赖，在编译的时候不依赖
* 默认的依赖范围是==complie==

#### 依赖传递

* ##### 那么当深度一样的时候Maven会如何选择呢？

  * A-->B,C

  > B-->D-->E(1.0)
  >
  > C-->E(2.0)

  **A会选择相对路径较短的组件E（2.0）依赖**

* ##### 那么当深度一样的时候Maven会如何选择呢？

  * A->B->D1.0

  * A->C->D2.0

    ##### 这种情况Maven会根据申明的依赖顺序来进行选择，先申明的会被作为依赖包。像前面这种情况，如果先申明对B的依赖，则A依赖的就是D1.0，如果先申明对C的依赖，则A依赖的就是D2.0。 

#### 使用exclusion排除依赖

假设有这样一种依赖关系，==A->B->C==，这个时候由于某些原因，我们不需要对 ==C==的依赖，但是我们又必须要对==B==的依赖，这个时候该怎么办呢？针对这种情况，Maven给我们提供了一个==exclusion==功能，我们可以==在添加A对B的依赖时申明不需要引进B对C的依赖==。具体做法如下：

**可选的依赖项**

可选的依赖项表示可有可无，不一定需要的，它只是做一个标记。为了便于大家理解，我们先看这样一种情况，假设项目==B-->C==，这个时候我们把B对C的依赖利用==optional==标记为可选的，它意味着B中只有部分地方用到了C，并不是必须要的，当你依赖于B，但是又不需要使用到B的C功能时，可以不依赖于C。这样当==A->B->C==时，在建立项目A的时候将不会加入对C的依赖，因为==C对B是可选的==，我们不一定会用到C。但是在建立项目B的时候，Maven就会加入对C的依赖。也就是说这种标记为optional的依赖项对项目本身而言是没有什么影响的，它影响的是以该项目作为依赖项的其他项目，如这里的项目A。这种可选的依赖项有一个好处就是它会默认的作为exclusion项排除。

```
<dependencies>  
       <dependency>  
              <groupId>groupB</groupId>  
              <artifactId>artifactB</artifactId>  
              <version>1.0</version>  
              <exclusions>  
                     <exclusion>  
                            <groupId>groupC</groupId>  
                            <artifactId>artifactC</artifactId>  
                     </exclusion>  
              </exclusions>  
       </dependency> 
       ...  
</dependencies>  
 <dependencies>  
       <dependency>  
              <groupId>groupB</groupId>  
              <artifactId>artifactB</artifactId>  
              <version>1.0</version>  
              <exclusions>  
                     <exclusion>  
                          <groupId>groupC</groupId>  
                           <artifactId>artifactC</artifactId>  
                     </exclusion>  
              </exclusions>  
       </dependency>  
       ...  
</dependencies>  
```

#### 继承

##### 	为何需要继承？

  我们知道Maven工程之间可以完成依赖的传递性，实际上就是各个jar包和war包之间存在依赖的传递性，但是必须是==compile范围的依赖才具有传递性==，才可以根据传递性统一的管理一个依赖的版本。而对于test范围的依赖，只是孤零零的存在于某个项目中，各个项目中的依赖版本可能不同，容易造成问题，所以==test范围的依赖的*统一版本*的问题依靠依赖的传递性是无法解决的==。所以我们使用继承这个概念来解决。 

##### 	怎么实现继承

  继承，顾名思义，存在着父子关系。我们需要定义一个统一管理某些test范围依赖的父工程，它打包的方式是pom。在它的pom文件中声明test范围的依赖的坐标。然后在子工程中以<parent>坐标</parent>的形式==声明父工程的坐标并且声明test范围的依赖坐标==，子工程中的也要声明test范围的依赖的坐标，但是需要注意的是必须将坐标中的版本号给去掉，才可以完成统一管理test范围依赖的版本。 

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/1d4ffdd7d307d0ed170f0c497d2c9fc71eedda32/123123.png)

ALL的配置 如下：

```
  <groupId>my</groupId>
  <artifactId>ALL</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>pom</packaging>
  <modules>
    <module>../A</module>
    <module>../B</module>
  </modules>
```

如果子项目A，B需要继承项目ALL中的POM配置，那么就需要使用到继承。在子项目中添加配置：

```
  <parent>
    <groupId>my</groupId>
    <artifactId>ALL</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </parent>
```

#### 聚合

#####   ① 为何需要聚合

  我们最终都要讲各个Maven工程安装到仓库中，但是由于==存在继承关系使得我们必选先安装父工程才可以安装子工程==，否则会报错。而且必须一个一个的install。那么能不能有一种更好的方式完成一键安装呢？聚合工程就可以完成。



 #####  ② 怎么实现聚合

  我们首先要==定义一个打包方式为pom的工程当做聚合工程== ，并且在其中用<models><model></model></models>标签的形式将一个一个Maven工程聚合进来，不必在意在model中的顺序，它会自动识别父工程来先完成安装。然后只有将这个聚合工程install那么其中聚合进来的工程就都可以顺利的install了。

  需要注意的是：**在实际项目开发工程中，我们可以使用同一个pom打包方式的工程来充当父工程和聚合工程**。即效果是其中的pom.xml文件包含test范围的依赖和models标签将各个Maven工程聚合进来。

#### Maven生命周期

Maven拥有三套相互独立的声明周期。

- clean
- default
- site

##### clean生命周期

用于清理项目， 包含三个阶段： 

*  pre-clean 执行清理前需要完成工作 
*  clean 清理上一次构建生成的文件 
* post-clean 执行清理后需要完成的工作 

##### default生命周期

定义了真正构建时所需要执行的步骤， 包含阶段如下 
validate 
initialize 
generate-sources 
process-sources 
generate-resources 
process-resources ：复制和处理资源文件到target目录，准备打包； 
compile ：编译项目的源代码； 
process-classes 
generate-test-sources 
process-test-sources 
generate-test-resources 
process-test-resources 
test-compile ：编译测试源代码； 
process-test-classes 
test ：运行测试代码； 
prepare-package 
package ：打包成jar或者war或者其他格式的分发包； 
pre-integration-test 
integration-test 
post-integration-test 
verify 
install ：将打好的包安装到本地仓库，供其他项目使用； 
deploy ：将打好的包安装到远程仓库，供其他项目使用；

##### site生命周期

pre-site 
site ：生成项目的站点文档； 
post-site 
site-deploy ：发布生成的站点文档

#### 配置插件

在POM文件中配置：

```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                    <compilerArgument>-Xlint:unchecked</compilerArgument>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>1.6.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>exec</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>12345678910111213141516171819202122232425262728
```

绑定到`maven-compiler-plugin:compile` 任务。 
source 和 target 指定编译的java为1.8版本。 
executions为配置一个执行子任务 
phase 配置生命周期阶段 
goals 执行插件的目标