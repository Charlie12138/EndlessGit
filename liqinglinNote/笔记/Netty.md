# Netty



## Java NIO

 同步非阻塞，服务器实现模式为==一个请求一个线程==，即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。

在[电子技术](https://zh.wikipedia.org/wiki/%E7%94%B5%E5%AD%90%E6%8A%80%E6%9C%AF)（特别是[数字电路](https://zh.wikipedia.org/wiki/%E6%95%B0%E5%AD%97%E7%94%B5%E8%B7%AF)）中，**数据选择器**（英语：**multiplexer**，简称：**MUX**[[1\]](https://zh.wikipedia.org/zh-hans/%E6%95%B0%E6%8D%AE%E9%80%89%E6%8B%A9%E5%99%A8#cite_note-wisegeek-1)），或称**多路复用器**，是一种可以从多个[模拟](https://zh.wikipedia.org/wiki/%E6%A8%A1%E6%93%AC%E4%BF%A1%E8%99%9F)或[数字](https://zh.wikipedia.org/wiki/%E6%95%B0%E5%AD%97%E4%BF%A1%E5%8F%B7)输入信号中选择一个信号进行输出的器件。[[2\]](https://zh.wikipedia.org/zh-hans/%E6%95%B0%E6%8D%AE%E9%80%89%E6%8B%A9%E5%99%A8#cite_note-Network+_Guide_to_Networks-2) 一个有 2*n* 输入端的数据选择器有 *n* 个可选择的输入－输出线路，可以通过控制端来选择其中一个信号被选择作为输出。[[3\]](https://zh.wikipedia.org/zh-hans/%E6%95%B0%E6%8D%AE%E9%80%89%E6%8B%A9%E5%99%A8#cite_note-3) 数据选择器主要用于增加一定量的时间和[带宽](https://zh.wikipedia.org/wiki/%E5%B8%A6%E5%AE%BD)内的可以通过[网络](https://zh.wikipedia.org/wiki/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%BD%91%E7%BB%9C)发送的数据量。[[2\]](https://zh.wikipedia.org/zh-hans/%E6%95%B0%E6%8D%AE%E9%80%89%E6%8B%A9%E5%99%A8#cite_note-Network+_Guide_to_Networks-2)

数据选择器使多个信号共享一个设备或资源，例如一个[模拟数字转换器](https://zh.wikipedia.org/wiki/%E6%A8%A1%E6%8B%9F%E6%95%B0%E5%AD%97%E8%BD%AC%E6%8D%A2%E5%99%A8)或一个传输线，而不必给每一个输入信号配备一个设备。

[了解一下NIO](https://www.jianshu.com/p/a9b2fec31fd1)



## 1.官网三例子：Discard

```java
public class DiscardServer {
	private int port;
	public DiscardServer(int port) {
		this.port = port;
	}

	public void run() throws InterruptedException {
        //这个是用于serversocketchannel的eventloop
		EventLoopGroup bossGroup = new NioEventLoopGroup();
         //这个是用于处理accept到的channel
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {

			ServerBootstrap b = new ServerBootstrap();//初始化服务的配置
            //设置时间循环对象，前者用来处理accept事件，后者用于处理已经建立的连接的io
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)//设定通讯模式为NIO，同步非阻塞
					/**
					 * childHandler是服务的Bootstrap独有的方法。是用于提供处理对象的。可以一次性
					 * 增加若干个处理逻辑。是类似责任链模式的处理方式。增加A，B两个处理逻辑，在处理                      *客户端请求数据的时候
					 * 根据A->B顺序依次处理
					 *
					 * ChannelInitializer 用于提供处理器的一个模型对象
					 * 其中定义了一个方法，initChannel方法。
					 * 该方法是用于初始化处理逻辑责任链条的。
					 * 可以保证服务端的Bootstrap只初始化一次处理器，尽量提供处理逻辑的重用。
					 * 避免反复的创建处理器对象，节约资源开销。
					 */
                    //为accept channel的pipeline预添加的inboundhandler
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
                         //当新连接accept的时候，这个方法会调用
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(new DiscardServerHandler());  //在这里配置具体数据接收方法的处理
						}
					})
					.option(ChannelOption.SO_BACKLOG, 128)//设定缓冲区大小，缓冲区的单位是字节
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			/**bind方法 绑定监听端口。ServerBootstrap可以绑定多个监听端口。多次调用bind方法即可
			 *sync 开始监听逻辑。返回一个ChannelFuture.返回结果代表的是监听成功后的一个对应的未来			  *结果
			 *bind方法会创建一个serverchannel，并且会将当前的channel注册到eventloop上面，
			 *会为其绑定本地端口，并对其进行初始化，为其的pipeline加一些默认的handler
			 *可以使用ChannelFuture实现后续的服务器和客户端的交互。
			 */
			ChannelFuture future = b.bind(port).sync();//绑定端口
			System.out.println("server start...");
             //相当于在这里阻塞，直到serverchannel关闭
			future.channel().closeFuture().sync();
		} finally {
			/**
			 * shutdownGracefully 方法是一个安全关闭的方法。可以保证不放弃任何一个已接收的客户端请求。
			 */
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt( args[0]);
		} else {
			port = 8080;
		}
		new DiscardServer(port).run();
	}

}

```

* [深入浅出EventLoop, EventLoopGroup](https://caorong.github.io/2016/12/24/head-first-netty-1/)

* ServerBootstrap的group方法：

```java
    //这里parent用于执行server的accept时间事件，child才是用于执行获取的channel连接的事件
    public ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup) {
        super.group(parentGroup);
        if (childGroup == null) {
            throw new NullPointerException("childGroup");
        }
        if (this.childGroup != null) {
            throw new IllegalStateException("childGroup set already");
        }
        this.childGroup = childGroup;
        return this;
    }

```

* channel方法:该方法主要是用于构造用于产生channel的工厂类，在我们这段代码说白了就是用于实例化serversocketchannel的工厂类。。。

  ```java
      //构造serversocketchannel factory
      public B channel(Class<? extends C> channelClass) {
          if (channelClass == null) {
              throw new NullPointerException("channelClass");
          }
          return channelFactory(new BootstrapChannelFactory<C>(channelClass));  //构造工厂类
      }
   
      /**
       * {@link ChannelFactory} which is used to create {@link Channel} instances from
       * when calling {@link #bind()}. This method is usually only used if {@link #channel(Class)}
       * is not working for you because of some more complex needs. If your {@link Channel} implementation
       * has a no-args constructor, its highly recommend to just use {@link #channel(Class)} for
       * simplify your code.
       */
      @SuppressWarnings("unchecked")
      public B channelFactory(ChannelFactory<? extends C> channelFactory) {
          if (channelFactory == null) {
              throw new NullPointerException("channelFactory");
          }
          if (this.channelFactory != null) {
              throw new IllegalStateException("channelFactory set already");
          }
   
          this.channelFactory = channelFactory;   //设置
          return (B) this;
      }
  
  ```

* childHandler方法：

  ```java
      //设置childHandler，这个是当有channel accept之后为其添加的handler
      public ServerBootstrap childHandler(ChannelHandler childHandler) {
          if (childHandler == null) {
              throw new NullPointerException("childHandler");
          }
          this.childHandler = childHandler;
          return this;
      }
  
  ```

* ServerBootstrap[的其他方法](https://blog.csdn.net/fjslovejhl/article/details/9300937)


## 2.Netty_TCP拆包粘包的解决方案：

```java
public class Server {

	public static void main(String[] args) throws Exception{
		.....
        .....
		 .childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				//设置特殊分隔符，并使用工具类，将String类型的$_转成ByeteBuf类型的数据
				ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
                /* DelimiterBasedFrameDecoder
				 * 作用主要是从特殊分隔符处，将报文进行拆分
				 * 2个参数：
				 * 第一个参数是指特殊分割符的最大长度
				 * 第二个参数是指特殊分隔符
				 */
				sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				//设置字符串形式的解码
				sc.pipeline().addLast(new StringDecoder());
				sc.pipeline().addLast(new ServerHandler());
			}
		});
		
		......
        ......
		
	}
	
}
```

```java
public class ServerHandler extends ChannelHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(" server channel active... ");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String request = (String)msg;
		System.out.println("Server :" + msg);
		String response = "服务器响应：" + msg + "$_";
		ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}

	......

}
```

```JAVA
public class Client {

	public static void main(String[] args) throws Exception {
		
		......
		 .handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				//
				ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
				sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				sc.pipeline().addLast(new StringDecoder());
				sc.pipeline().addLast(new ClientHandler());
			}
		});
		
		......
	}
}
```

```java
public class ClientHandler extends ChannelHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channel active... ");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			String response = (String)msg;
			System.out.println("Client: " + response);
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
.....
}
```

