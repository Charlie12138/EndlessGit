package lql.websocket;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
