# 运用maven和mybatis将二轮项目重构遇到的问题

#### 1.IDEA_maven依赖错误 包下面红色波浪线

:call_me_hand:**解决**：修改pom 配置文件，讲标红的依赖先删除，并点击reimport, 之后重新加上出错的依赖，再reimport 

##### 2.依赖少配置了javax.servlet-api，导致出现错误。