package lql.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;

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
			System.out.println(this.toString());
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
