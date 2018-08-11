## HTTP请求和相应



1.什么是TCP协议

**传输控制协议**（英语：**T**ransmission **C**ontrol **P**rotocol，缩写为**TCP**）是一种面向连接的、可靠的、基于字节流的[传输层](https://zh.wikipedia.org/wiki/%E4%BC%A0%E8%BE%93%E5%B1%82)通信协议。在简化的计算机网络[OSI模型](https://zh.wikipedia.org/wiki/OSI%E6%A8%A1%E5%9E%8B)中，它完成第四层传输层所指定的功能，[用户数据包协议](https://zh.wikipedia.org/wiki/%E7%94%A8%E6%88%B7%E6%95%B0%E6%8D%AE%E5%8C%85%E5%8D%8F%E8%AE%AE)（UDP）是同一层内另一个重要的传输协议。

------

![https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/05.d01z.011.png](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/05.d01z.011.png)



![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/3985563-ecf824604debcdf1.png)

------



### 一次完整的HTTP通信过程中, Web浏览器与Web服务器之间将完成下列7个步骤: 

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/3985563-ef43bfa84bb68de1.png)



##### 1.建立TCP连接（三次握手）



##### 2.Web浏览器发送请求命令

一旦建立了TCP连接, Web浏览器就会向Web服务器发送请求命令 例如：GET/sample/hello.jsp HTTP/1.1 



##### 3.Web浏览器发送请求头信息

浏览器发送其请求命令之后, 还要以头信息的形式向Web服务器发送一些别的信息, 这些信息用来描述浏览器自己。之后浏览器发送了一空白行来通知服务器, 表示它已经结束了该头信息的发送. 若是post请求, 还会在发送完请求头信息之后发送请求体. 

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/3985563-cd59a3899ef546e1.png)



##### 4.Web服务器应答

客户机向服务器发出请求后, 服务器会向客户机回送应答. `HTTP/1.1 200 OK` 应答的第一部分是协议的版本号和应答状态码 



##### 5.Web服务器发送应答头信息

正如客户端会随同请求发送关于自身的信息一样,服务器也会随同应答向用户发送关于它自己的数据及被请求的文档. 最后以一个空白行来表示头信息发送到此结束.

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/3985563-c6ee8f8526f59fc0.png)



##### 6.Web服务器向浏览器发送数据

Web服务器向浏览器发送头信息后, 它就以`Content-Type`应答头信息所描述的格式发送用户所请求的实际数据

```markdown
相应报文实例：

HTTP/1.1 200 OK　　状态行
Date: Sun, 17 Mar 2013 08:12:54 GMT　  响应头部
Server: Apache/2.2.8 (Win32) PHP/5.2.5
X-Powered-By: PHP/5.2.5
Set-Cookie: PHPSESSID=c0huq7pdkmm5gg6osoe3mgjmm3; path=/
Expires: Thu, 19 Nov 1981 08:52:00 GMT
Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
Pragma: no-cache
Content-Length: 4393
Keep-Alive: timeout=5, max=100
Connection: Keep-Alive
Content-Type: text/html; charset=utf-8
　　空行
<html>　　响应数据
<head>
<title>HTTP响应示例<title>
</head>
<body>
Hello HTTP!
</body>
</html>
```

```
状态码

状态代码为3位数字。
1xx：指示信息--表示请求已接收，继续处理。
2xx：成功--表示请求已被成功接收、理解、接受。
3xx：重定向--要完成请求必须进行更进一步的操作。
4xx：客户端错误--请求有语法错误或请求无法实现。
5xx：服务器端错误--服务器未能实现合法的请求。
```



##### 7 Web服务器关闭TCP连接

一般情况下, 一旦Web服务器向浏览器发送了请求数据, 它就要关闭TCP连接. 如果浏览器或者服务器在其头信息加入了这行代码
`Connection:keep-alive`
TCP连接在发送后将仍然保持打开状态. 于是, 浏览器可以继续通过相同的连接发送请求. 保持连接节省了为每个请求建立新连接所需的时间, 还节约了网络带宽.

------



### last.断开TCP连接（四次挥手）：

###### 1.当主机1发出FIN报文段时，只是表示主机1已经没有数据要发送了，主机1告诉主机2，它的数据已经全部发送完毕了；

###### 2.这个时候主机1还是可以接受来自主机2的数据；当主机2返回ACK报文段时，表示它已经知道主机1没有数据发送了，但是主机2还是可以发送数据到主机1的；

###### 3.当主机2也发送了FIN报文段时，这个时候就表示主机2也没有数据要发送了，就会告诉主机1，我也没有数据要发送了;

###### 4.之后彼此就会愉快的中断这次TCP连接。 

![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/3985563-c1c59148f8b26c43.png)







### 浅谈HTTPS

我们都知道HTTP并非是安全传输，在HTTPS基础上使用SSL协议进行加密构成的HTTPS协议是相对安全的。目前越来越多的企业选择使用HTTPS协议与用户进行通信，如百度、谷歌等。HTTPS在传输数据之前需要客户端（浏览器）与服务端（网站）之间进行一次握手，在握手过程中将确立双方加密传输数据的密码信息。**一言以弊之，HTTPS是通过一次非对称加密算法（如RSA算法）进行了协商密钥的生成与交换，然后在后续通信过程中就使用协商密钥进行对称加密通信**。HTTPS协议传输的原理和过程简图如下所示：

 ![](https://raw.githubusercontent.com/Charlie12138/EndlessGit/master/picture/1430132-d66d71b8bc4c14b1.png)

 

1. 客户端发起明文请求：将自己支持的一套==加密规则、以及一个随机数（Random_C）==发送给服务器。

   

2. 服务器初步响应：服务器根据自己支持的加密规则，从客户端发来的请求中选出一组加密算法与HASH算法，生成随机数，并将自己的身份信息以证书（CA）的形式发回给浏览器。CA证书里面包含了服务器地址，加密公钥，以及证书的颁发机构等信息。这时服务器给客户端的包括选择使用的==加密规则、CA证书、一个随机数（Random_S）==。

   

3. 客户端接到服务器的初步响应后做四件事情：
    （1）==证书校验==： 验证证书的合法性（颁发证书的机构是否合法，证书中包含的网站地址是否与正在访问的地址一致等）。
    （2）==生成密码==：浏览器会生成一串随机数的密码（Pre_master），并用CA证书里的公钥加密==(enc_pre_master)==，用于传给服务器。
    （3）计算协商密钥：
    此时客户端已经获取全部的计算协商密钥需要的信息：两个明文随机数= = Random_C 和 Random_S == 与自己计算产生的 Pre-master，计算得到协商密钥==enc_key==。
    enc_key=Fuc(random_C, random_S, Pre-Master)
    （4）生成==握手信息==：使用约定好的HASH计算握手消息，并使用协商密钥enc_key及约定好的算法对消息进行加密。

   

4. 客户端将第三步产生的数据发给服务器： 这里要发送的数据有三条： 

   （1）用公钥加密过的服务器随机数密码enc_pre_master 

   （2）客户端发给服务器的通知，"以后我们都要用约定好的算法和协商密钥进行通信的哦"。 

   （3）客户端加密生成的握手信息。 

   

5. 服务器接收客户端发来的数据要做以下四件事情：

   （1）私钥解密：使用自己的私钥从接收到的enc_pre_master中解密取出密码==Pre_master==。

   

   （2）计算协商密钥：此时服务器已经获取全部的计算协商密钥需要的信息：两个明文随机数 ==Random_C== 和 			 ==Random_S==与==Pre-master==，计算得到协商密钥==enc_key==。 enc_key=Fuc(random_C, random_S, Pre-Master

   

    (3）解密握手消息：使用协商密钥enc_key解密客户端发来的握手消息，并验证HASH是否与客户端发来的一致。

​      （4）生成==握手消息==使用协商密钥enc_key及约定好的算法加密一段握手消息，发送给客户端。



6.服务器将第五步产生的数据发给客户端，这里要发的数据有两条： 

（1）服务器发给客户端的通知，”听你的，以后我们就用约定好的算法和协商密钥进行通信哦“。 

（2）服务器加密生成的握手信息。 

 

7.客户端拿到握手信息解密，握手结束。 客户端解密并计算握手消息的HASH，如果与服务端发来的HASH一致，此时握手过程结束。 

 

 8.正常加密通信 握手成功之后，所有的通信数据将由之前协商密钥==enc_key==及约定好的算法进行加密解密。 

 

 

 

 

 

 

 

 

 

 