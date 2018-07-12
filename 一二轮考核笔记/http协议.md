* HTTP是应用层协议，上网浏览网页的时候，浏览器和服务器之间就会通过http在Internet上进行数据的发送和接收。
* HTTP是一个基于请求/响应模式的，无状态的协议(request / response based, stateless protocol)
* 基于请求/响应模式:输入网址-->浏览器将请求发送给服务器-->解析IP地址-->响应
* 无状态：不会记住是不是同一个请求是不是同一个人发的。
* http1.1给出了持续链接机制。
#### 浏览器与服务器连接的一般过程：
```
    ServerSocket serverSocket  =new SERverSocket();
    www.sohu.com + 80(浏览器自动加的) ->DNS(Domain Name Service)->221.179.180.20:80
    Socket socket = new Socket("221.179.180.20",80);
```
* URL是一种特殊类型的URI. 
* HTTP有三部分组成：
> 请求行

> 消息报头

> 请求正文