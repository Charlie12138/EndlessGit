# **Maven**

## Maven项目的结构

- pom.xml

- src

  - main
    - java
      - package
  - test
    - java
      - package
  - resources

- target(调用mvn:compile后出现)，里面是编译好的.class等等

- ps：resources文件夹中放资源文件

- **pom.xml文件中必备的东西**：

  ```xml
  <groupId>项目名的项目组，默认为包名，xxx.xxx.xxxx的形式</groupId>
  
  <artifactId>模块名称，通常为项目名-模块名			</artifactId>
  
  <version>1.0-SNAPSHOT</version>
  ```

  

## 基本操作:happy:

- 编译：**mvn compile**

- 测试：**mvn test**

- 清空(将目录中的target文件夹删除)：**mvn clean**

- 将项目打包成一个jar包，放在target目录下：**mvn package**

- 将打包好的jar包引入仓库中：**mvn install**

  ps：仓库位于c:/Users/10648/.m2

- 编译时所需要的jar包若仓库中没有，会自动从网上下载，因此Maven需要联网使用

- **仓库中jar包的作用**：很多项目的一些模块是通用的，如baseDAO等，在项目中通过pom.xml进行引入(就是**依赖**)，格式如下：

  ```xml
  <dependencies>
  
  	<dependency>
  
  		<groupId>xxxx</groupId>
  
  		<artifactId>xxxx</artifactId>
  
  		<version>xxxxxx</version>
          
          <scope>六个值之一，没有就默认为compile</scope>
  
  	</dependency>
  
  </dependencies>
  
  ```

- 若Maven仓库丢失，要去全球的中央工厂中下载

  http://mvnrepository.com/

### Maven内置隐式变量:smiley:

- 项目根目录：**${basedir}**
- 构建目录，缺省为target：**${project.build.directory}**
- 构建过程输出目录，缺省为target/classes：**${project.build.outputDirectory}**
- 打包类型，缺省为jar：**${project.packaging}**
- 当前pom文件的任意节点的内容：**${project.xxx}**

---

## 依赖

- 很多项目的一些模块是通用的，如baseDAO等，在项目中通过pom.xml进行引入(就是**依赖**)，格式如下 ：

  ```xml
  <dependencies>
  
  	<dependency>
  
  		<groupId>xxxx</groupId>
  
  		<artifactId>xxxx</artifactId>
  
  		<version>xxxxxx</version>
          
          <scope>六个值之一，没有就默认为compile</scope>
  
  	</dependency>
  
  </dependencies>
  ```

  

### 作用域**<scope>**的六个值

- **compile**：**默认依赖范围**。**编译**范围有效，在**编译测试打包**时都会将依赖存储进去。

- import

- **provided**：**编译**和**测试**的时候会把包加进去，打包（即最后生成jar包）的时候不会

  eg：servlet-api，因为它在tomcat等web服务器已经存在了，如果再打包会冲突

- **runtime**：在**测试运行**的时候依赖，在编译的时候不依赖

  eg：mysql-connector

- system

- **test**：**测试**范围有效，在编译和打包时都不会使用这个依赖

### 依赖传递

- 只传递**<scope>**为**compile**的包

- **间接依赖**:sob:：

  1. 依赖级别相同：如A依赖于L1.0，B依赖于L2.0，C同时依赖A,B，**则C依赖的pom.xml中先声明的那个依赖**，而不是根据jar包的版本高低来依赖
  2. 依赖级别不同：用**依赖层次最短**的那一个

- **排除依赖**：当产生包冲突时，在**<dependency>**中使用：

  ```xml
  <exclusions>
  
  	<exclusion>
  
  		<groupId>xxx</groupId>
  
  		<artifactId>xxx</artifactId>
  
  	</exclusion>
  
  </exclusions>
  
  ```

## 聚合和继承

- **继承和聚合可以在同一个pom.xml文件中完成**，该文件位于所有模块的根目录下
- 继承的路径最后是一个pom.xml文件，聚合是模块的位置

### 聚合

- 当项目模块化时，要运行项目不可能将每个模块都编译一次（模块一多效率很低），因此在模块的根目录下**添加一个pom.xml文件**，通过这个文件来集中编译各个模块中的项目。**这个pom.xml文件不用写任何内容，它的用途就是聚合操作**

- 在这个pom.xml文件中加入

  ```xml
  <modules>
      <module>模块相对于该pom.xml文件的路径</module>
      <module>同上</module>
      .....（个数为需要聚合的模块数量）
  </modules>    
  
  ```

- 要针对**整个**项目作**编译测试运行**等操作时，就可以直接对pom.xml文件做操作即可，操作顺序按照pom.xml中**<module>**的顺序

### 继承

- 当多个模块中的pon.xml文件中的配置有大量重复时，让所有的项目模块继承于一个根类（一个空项目），在根类里配置公有的东西

- 用法：在子模块项目中添加如下代码：

  ```xml
  <parent>
  	<groupId>xxxx</groupId>
      <artifactId>根模块的名称</artifactId>
      <version>xxxx</version>
      <relativePath>根模块的名称/pom.xml
      </relativePath>
  </parent>
  ```

- 依赖也可以继承，**这样在子模块中就不用再写版本信息和作用域等公有的东西了（不用<version>等**），只要写groupId和artifactId：在父模块的pom.xml文件中添加如下代码：

  ```xml
  <dependencyManagement>
  	<dependencies>
          <dependency>
          	<groupId>xxxx</groupId>
          	<artifactId>xxxx</artifactId>
              <version>xxxx</version>
              <scope>xxxx</scope>
          </dependency>
          ....
      </dependencies>
  </dependencyManagement>
  ```


## 生命周期

### 三套生命周期互相独立:sweat_smile:

- site（不太重要）：当项目做完后，通过site的生命周期自动生成站点，但是我们目前需要用到的地方不多

- **clean**

  1. pre-clean：执行一些需要在clean之前完成的工作
  2. clean：移除所有上一次构建生成的文件
  3. post-clean：执行一些需要在clean之后立刻完成的工作

- **compile/default**：执行的步骤很多，加粗的为重要的

  1. validate：验证，生成源代码
  2. generate-sources：生成资源文件
  3. process-sources
  4. generate-resources
  5. **process-resources**：复制并处理资源文件，至目标目录，准备打包
  6. compile：编译项目的源代码

  （**执行mvn compile即会执行上面的6步**）

  - process-classes
  - generate-test-sources
  - process-test-sources
  - generate-test-resources

  - **process-test-resources**：复制并处理资源文件，至目标测试目录
  - **test-compile**：编译测试源代码
  - process-test-classes
  - **test**：使用合适的单元测试框架运行测试，这些测试代码不会被打包或部署

  （**执行mvn test即会从开始执行到test这一步**）

  - prepare-package
  - **package**：接受编译好的代码，打包成可发布的格式，jar

  （**执行mvn package即会从开始执行到package这一步**）

  - pre-integration-test
  - integration-test
  - post-integration-test
  - verify

  - **install**：将包安装至本地仓库，让其他项目依赖

  （**执行mvn install即会从开始执行到install这一步**）

  - **deploy**(部署)：将最终的包复制到远程的仓库，让其他开发人员与项目共享

### 插件和目标

- 上面的每一个步骤都是由**插件**来执行的；每一个步骤就对应**每一个插件执行的每一个目标**

- 各个目标是包含在插件中的，在相应的插件中可以开发多个目标，在生命周期中捆绑不同的目标

-  插件全部保存在本地仓库中。我的目录为：E:\Maven\maven\repository\org\apache\maven\plugins

- 插件支持生命周期的正常运转

- **绑定插件并规定插件在哪一个生命周期执行**：

  ```xml
  <build>
  	<plugins>
      	<plugin>
          	<groupId>xxx</groupId>
     			<artifactId>xxx</artifactId>
          	<version>xxx</version>
              <!---上面三个是插件本身的信息--->
              <!---下面是规定插件在哪一个生命周期执			行-->
              <executions>
              	<execution>
                      <phase>上面的步骤之一，在该步							骤之后执行该插件
                      </phase>
                      <goals>
                          <goal>去maven官网找自己							想要的目标							</goal>
                          ....
                      </goals>
                  </execution>
              </executions>
          </plugin>
      </plugins>
  </build>
  ```

- 每一个插件具体是如何执行的一定要去maven官网上查看帮助文档，或者看源代码（不推荐）

- 引用的插件同样可以**继承**，**这样在子模块中就不用再写版本信息和作用域等公有的东西了（不用<version>等**），只要写groupId和artifactId，在父模块的pom.xml文件中添加如下代码：（和依赖的继承类似）

  ```xml
  <build>
  	<pluginManagement>
      	<plugins>
          	<plugin>
                  ...(和上一个代码块中类似)
              </plugin>
          </plugins>
      </pluginManagement>
  </build>
  ```

- **插件里面也可以加依赖**，如sql插件需要依赖JDBC，但是仅仅只在插件编译的时候有用

- **插件的设置**，如mysql：

  ```xml
  <plugin>
      <configuration>
          <driver>xxx</driver>
          <url>xxx</url>
          <username>xxx</username>
          <password>xxx</password>
      </configuration>
  </plugin>
  ```

## 测试

- 主要的测试框架：**JUnit**

- 管理测试的插件：**surefire**

- **surefire**默认只会执行这几个测试：

  1. Test**：以Test开头的文件
  2. **Test：以Test结尾的文件
  3. **TestCase：以TestCase结尾的文件

- 如果希望执行测试命令时测试除以上3种的文件（.java） ，则需要在surefire的**<plugin>**中另外配置以下代码：（该例子为测试Hello开头的.java文件，排除Test开头的.java文件）

  ```xml
  <configuration>
  	<includes>
          <include>**/Hello*.java</include>
      </includes>
      <excludes>
          <exclude>**/Test*.java</exclude>
      </excludes>
  </configuration>
  ```

- 如果需要跳过测试，则在**<configuration>**中加上：

  ```xml
  <skip>true</skip>
  ```

- 在测试时动态的加上参数 **-Dtest=Hello.java**，则可以只测试这Hello.java这一个测试类，在修改了单个测试类时，十分有用