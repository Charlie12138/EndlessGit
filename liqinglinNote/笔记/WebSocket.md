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
