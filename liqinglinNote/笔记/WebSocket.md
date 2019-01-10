# WebSocket

一、协议简介

WebSocket protocol 是HTML5一种新的协议，它是实现了浏览器与服务器全双工通信。

WebSocket通信过程分为两步：握手请求和数据传输。

## 1.特点

- 单一的[TCP连接](https://blog.csdn.net/ns_code/article/details/29382883)，采用全双工模式通信。
- 对代理、防火墙和路由器透明。
- 无头部信息、Cookie和身份验证。
- 无安全开销。
- 通过“ping/pong”帧保持链路激活。
- 服务器可以主动传递消息给客户端，不再需要客户端轮询。

## 2.连接建立：

![](http://www.ruanyifeng.com/blogimg/asset/2017/bg2017051502.png)

客户端发出的握手信息类似：(是HTTP升级版的请求)

```xml
GET /chat HTTP/1.1
Host: server.example.com
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
Origin: http://example.com
Sec-WebSocket-Protocol: chat, superchat
Sec-WebSocket-Version: 13
```

* 跟在 GET 方法后面的 “请求标识符 Request-URI” 是用于区别 WebSocket 链接到的不同终节点。一个 IP 可以对应服务于多个域名，这样一台机器上就可以跑多个站点，然后通过 “请求标识符”，单个站点中又可以含有多个 WebSocket 终节点。

* Host 头中的服务器名称可以让客户端标识出哪个站点是其需要访问的，也使得服务器得知哪个站点是客户端需要请求的。

服务端回应的握手信息类式：

```xml
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
Sec-WebSocket-Protocol: chat
```



- 握手成功后，服务端和客户端就可以通过“message”的方式进行通信了，一个消息由一个或多个帧组成，WebSocket的消息并不一定对应一个特定的网络层的帧，它可以被分割成多个帧或者被合并。帧都有自己的类型，属于同一个消息的多个帧具有相同类型的数据。

* 服务端为了告知客户端它已经接收到了客户端的握手请求，服务端需要返回一个握手响应。在服务端的握手响应中，需要包含两部分的信息。第一部分的信息来自于客户端的握手请求中的 `Sec-WebSocket-Key` 头字段：

  ```
  Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
  ```

服务端接收请求后主要是成针对Sec-WebSocket-Key生成相应的加密key,采用的算法是sha1算法库，生成key后，和客户端握手成功。

## 3.关闭握手

任意一端都可以选择关闭握手过程。==需要关闭握手的一方==通过发送一个==特定的控制序列==去开始一个关闭握手的过程。==一端==一旦接受到了来自另一端的==请求关闭控制帧==后，==接收到关闭请求的一端==如果还没有==返回一个作为响应的关闭帧==的话，那么它需要==先发送一个关闭帧==。在接受到了对方响应的关闭帧之后，发起关闭请求的那一端就可以==关闭连接==了。

在发送了请求关闭控制序列之后，==发送请求的一端将不可以再发送其他的数据内容==；同样的，一但接收到了一端的请求关闭控制序列之后，==来自那一端的其他数据内容将被忽略==。注意这里的说的是数据内容，==控制帧还是可以响应的==。

两边同时发起关闭请求也是可以的。

之所以需要这样做，是因为客户端和服务器之间可能还存在其他的中间件。一端关闭之后，也需要通知另一端也和中间件断开连接。

## 4.Java Websocket——服务器端

* **@ServerEndpoin**: 告诉Java平台它注解的类实际上要成为一个WebSocket端点。

* **@OnOpen**: 当此连接建立新的连接时调用此方法。

  * WebSocket Session对象，用于表示已经建立好的连接。

* **@OnMessage**: 

  This method level annotation can be used to make a Java method receive incoming web socket messages

* **@OnError：**可以不处理。

  伴随的信息是：

  1.错误消息；

  2.发生错误的会话；

  3.建立连接的打开阶段握手相关联的任何一个路径参数。

* **@OnClose**

  伴随的信息是：

  1.关闭信息；

  2.建立连接的打开阶段握手相关联的任何一个路径参数。

  3.连接关闭的原因的信息。（以CloseReason类的形式存在）

* **session.getBasicRemote().sendText(json.toString())：**

  服务端给客户端发消息是通过session来完成的，想要对指定用户发送消息就得拿到对方用户的session，用它来发送消息。

  session.getBasicRemote().sendText(json.toString()); 其实就是给指定用户发消息，这里的session就是该用户的session。

```java
//告诉Java平台它注解的类实际上要成为一个WebSocket端点
@ServerEndpoint(value = "/websocket/chat")
public class ChatClient {
	private static final Logger logger = LoggerFactory.getLogger(ChatClient.class);
	private static final String GUEST_PREFIX = "Guest";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	private static final Map<String, Object> connections = new HashMap<>();
	private final String nickname;
	private Session session;

	public ChatClient() {
		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
	}

    //当此连接建立新的连接时调用此方法
	@OnOpen
	public void start(Session session) {
		this.session = session;
		connections.put(nickname, this);
		String message = String.format("* %s %s", nickname, "has joined.");
		broadcast(message);
	}

	@OnClose
	public void end() {
		connections.remove(nickname);
		String message = String.format("* %s %s", nickname, "has disconnected.");
		broadcast(message);
	}

	/**
	 * 消息触发发送方法
	 * @param message
	 */
	@OnMessage
	public void incoming(String message) {
		String filteredMessage = String.format("%s: %s", nickname, HTMLFilter.filter(message.toString()));
		broadcast(filteredMessage);
	}

	@OnError
	public void onError(Throwable t) {
		logger.error("Chat Error: " + t.toString(), t);
	}

	/**
	 * 消息发送的方法
	 * @param msg
	 */
	public static void broadcast(String msg) {
//		if (msg.indexOf("Guest0") != -1) {
//			sendUser(msg);
//		} else {
//		}
			sendAll(msg);
	}

	/**
	 * 向所有用户发信息
	 * @param msg
	 */
	public static void sendAll(String msg) {
		for (String key : connections.keySet()) {
			ChatClient chatClient = null;
			try {
				chatClient = (ChatClient)connections.get(key);
				synchronized (chatClient) {
					chatClient.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				doubledd(chatClient, e);
			}
		}
	}

	/**
	 * 向指定用户发送消息
	 * @param msg
	 */
	public static void sendUser(String msg){
		ChatClient chatClient = (ChatClient) connections.get("Guest0");
		try {
			/**
			*getBasicRemote()：return a reference a RemoteEndpoint object representing             *the peer of this conversation
			*that is able to send messages synchronously to the peer
			* 服务端给客户端发消息是通过session来完成的，想要指定用户发送消息就得拿到对方用户的                 *session，用它来发送消息。
			* session.getBasicRemote().sendText(json.toString()); 其实就是给指定用户发消               *息，这里的session就是该用户的session。
			*/
			chatClient.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			doubledd(chatClient, e);
		}
	}

	private static void doubledd(ChatClient chatClient, IOException e) {
		logger.debug("Chat Error: Failed to send message to client", e);
		connections.remove(chatClient);
		try {
			chatClient.session.close();
		} catch (IOException e1) {

		}
		String message = String.format("* %s %s", chatClient.nickname, "has been disconnected.");
		broadcast(message);
	}
}
```



## 5.spring + springmvc + websocket



### 1）、maven依赖

```xml
1.spring的各种依赖
2. <!--websocket依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-orm</artifactId>
            <version>4.2.4.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-websocket</artifactId>
            <version>4.0.6.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-messaging</artifactId>
            <version>4.0.6.RELEASE</version>
        </dependency>
```

### 2）、web.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         id="WebApp_ID" version="3.1">

    <!-- 加载Spring容器配置 -->
    <listener>
        <listenerclass>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!-- Spring容器加载所有的配置文件的路径 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>

    <!-- 配置SpringMVC核心控制器,将所有的请求(除了刚刚Spring MVC中的静态资源请求)都交给Spring MVC -->
    <servlet>
        <servlet-name>springMvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-websocket.xml</param-value>
        </init-param>

        <!--用来标记是否在项目启动时就加在此Servlet,0或正数表示容器在应用启动时就加载这个Servlet,
        当是一个负数时或者没有指定时，则指示容器在该servlet被选择时才加载.正数值越小启动优先值越高  -->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!--为DispatcherServlet建立映射-->
    <servlet-mapping>
        <servlet-name>springMvc</servlet-name>
        <!-- 拦截所有请求,千万注意是(/)而不是(/*) -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

### 3)、spring-websocket.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:websocket="http://www.springframework.org/schema/websocket"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/websocket http://www.springframework.org/schema/websocket/spring-websocket.xsd">
    <context:component-scan base-package="lql"/>
    <!-- 告知Spring，我们启用注解驱动 -->
    <mvc:annotation-driven/>
    <!-- org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler，
    它会像一个检查员，对进入DispatcherServlet的URL进行筛查，如果发现是静态资源的请求，
    就将该请求转由Web应用服务器默认的Servlet处理，如果不是静态资源的请求，才由DispatcherServlet继续处理。 -->
    <mvc:default-servlet-handler/>
    <!-- 对静态资源文件的访问,因为Spring MVC会拦截所有请求,导致jsp页面中对js和CSS的引用也被拦截,配置后可以把对资源的请求交给项目的
    默认拦截器而不是Spring MVC-->
    <!--<mvc:resources mapping="/static/**" location="" />-->

    <!-- websocket处理类 -->
    <bean id="msgHandler" class="lql.websocket.MyWebSocketHandler"/>
    <!-- 握手接口/拦截器 ，看项目需求是否需要-->
    <bean id="handshakeInterceptor" class="lql.websocket.MyHandshakeInterceptor"/>
    <websocket:handlers>
        <websocket:mapping path="/websocket" handler="msgHandler"/>
        <websocket:handshake-interceptors>
            <ref bean="handshakeInterceptor"/>
        </websocket:handshake-interceptors>
    </websocket:handlers>
    <!-- 注册 sockJS,sockJs是spring对不能使用websocket协议的客户端提供一种模拟 -->
    <websocket:handlers>
        <websocket:mapping path="/sockjs/websocket" handler="msgHandler"/>
        <websocket:handshake-interceptors>
            <ref bean="handshakeInterceptor"/>
        </websocket:handshake-interceptors>
        <websocket:sockjs/>
    </websocket:handlers>

    <!-- 配置Spring MVC的视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 有时我们需要访问JSP页面,可理解为在控制器controller的返回值加前缀和后缀,变成一个可用的URL地址 -->
        <property name="prefix" value=""/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

### 4)、java

配置类

```java
/**
 * 在WebSocketConfig类中，我们为MyWebSocketHandler注册了两个url请求，并应用了我们所定义的MyHandshakeInterceptor拦截器。
 */
@Configuration
@EnableWebSocket
public class WebSocketServerConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//注册处理拦截器,拦截url为websocket的请求
		registry.addHandler(new MyWebSocketHandler(), "/websocket").addInterceptors(new MyHandshakeInterceptor());
		//注册SockJs的处理拦截器,拦截url为/sockjs/socketServer的请求
		registry.addHandler(new MyWebSocketHandler(),"/sockjs/websocket").addInterceptors(new MyHandshakeInterceptor()).withSockJS();
	}
}
```

握手拦截器，继承 HandshakeInterceptor类，做一些连接握手或者握手后的一些处理

```java
/**
 * 在执行客户端服务器端握手之前，也就是在beforeHandshake()方法中，
 * 我们将HttpSession中我们登录后存储的对象放到WebSocketSession中，以此实现定向发送消息。
 */
public class MyHandshakeInterceptor implements HandshakeInterceptor {

	//初次握手访问前
	@Override
	public boolean beforeHandshake(ServerHttpRequest serverHttpRequest,
								   ServerHttpResponse serverHttpResponse,
								   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
		if(serverHttpRequest instanceof ServletServerHttpRequest) {
			HttpServletRequest servletRequest = ((ServletServerHttpRequest)serverHttpRequest).getServletRequest();
			if(servletRequest.getSession() != null) {
				String username = (String)servletRequest.getSession().getAttribute("name");
				System.out.println("获取session里面的name=======" + username);
				//使用username区分WebSocketHandler，以便定向发送消息
				map.put("WEBSOCKET_USERNAME", username);
				servletRequest.getSession().setAttribute("WEBSOCKET_USERNAME", username);
			}
		}
		return true;
	}
	//初次握手访问后
	@Override
	public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
		System.out.println("初次握手访问后");
	}
}
```

创建MyWebSocketHandler类继承WebSocketHandler类（Spring提供的有AbstractWebSocketHandler类、TextWebSocketHandler类、BinaryWebSocketHandler类，看自己需要进行继承），该类主要是用来处理消息的接收和发送

```java
public class MyWebSocketHandler implements WebSocketHandler {
	private Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);

	//保存用户链接
	private static ArrayList<WebSocketSession> users = new ArrayList<>();

	private String username = "";

	//初次连接就绪时
	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		logger.trace("连接成功。。。。。。");
		users.add(webSocketSession);
		username = webSocketSession.getAttributes().get("WEBSOCKET_USERNAME").toString();
		System.out.println("用户：" + username + "登录了");
		if(username != null) {
			webSocketSession.sendMessage(new TextMessage("我们已经成功建立socket通信了"));
		}
	}
	// 处理信息
	@Override
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
		sendMessageToUsers(new TextMessage(webSocketMessage.getPayload() + ""));
	}
	// 处理传输时异常
	@Override
	public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
		if (webSocketSession.isOpen()) {
			webSocketSession.close();
		}
		logger.info("链接出错，关闭链接......");
		System.out.println("链接出错，关闭链接......");
		users.remove(webSocketSession);
	}
	// 关闭 连接时
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		logger.info("链接关闭......" + closeStatus.toString());
		System.out.println("链接关闭......" + closeStatus.toString());
		users.remove(webSocketSession);
	}
	//是否支持分包
	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	//给所有在线用户发送消息
	public void sendMessageToUsers(TextMessage message) {
		for(WebSocketSession user : users) {
			System.out.println(user.getAttributes().get("WEBSOCKET_USERNAME"));
			try{
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException E){
				E.printStackTrace();
			}
		}
	}

	//给特定用户发送消息
	public void sendToUser(String username, TextMessage message) {
		for(WebSocketSession user : users) {
			try{
				if (user.isOpen() && user.getAttributes().get("WEBSOCKET_USERNAME").equals(username)) {
					user.sendMessage(message);
				}
			} catch (IOException E){
				E.printStackTrace();
			}
		}
	}
}
```

controller

```java
@Controller
public class SocketController {

	@Autowired
	MyWebSocketHandler myWebSocketHandler;

	@RequestMapping("/login1")
	public String login1(HttpSession session) {
		session.setAttribute("name", "LQL");
		return "index";
	}

	@RequestMapping("/login2")
	public String login2(HttpSession session) {
		session.setAttribute("name", "lql");
		return "index";
	}

	@RequestMapping("/login3")
	public String login3(HttpSession session) {
		session.setAttribute("name", "lQl");
		return "index";
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String sendMessage() {
		myWebSocketHandler.sendToUser("LQL", new TextMessage("这是一条测试的消息"));
		return "index";
	}
}

```

简单的前端代码

````java
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Java后端WebSocket的Tomcat实现</title>
</head>
<body>
Welcome<br/><input id="text" type="text"/>
<button onclick="send()">发送消息</button>
<hr/>
<button onclick="closeWebSocket()">关闭WebSocket连接</button>
<hr/>
<div id="message"></div>
</body>

<script type="text/javascript">
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/websocket");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
</html>
````

