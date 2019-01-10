package lql.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;


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
