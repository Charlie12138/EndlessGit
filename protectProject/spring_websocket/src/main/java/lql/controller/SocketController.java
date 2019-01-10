package lql.controller;

import lql.websocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

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
		session.setAttribute("name", "abc");
		return "index";
	}

	@RequestMapping("/login3")
	public String login3(HttpSession session) {
		session.setAttribute("name", "kkk");
		return "index";
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String sendMessage() {
		myWebSocketHandler.sendToUser("LQL", new TextMessage("这是一条测试的消息"));
		return "index";
	}
}


