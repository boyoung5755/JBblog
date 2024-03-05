package com.innovationT.blog.websocket.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	
	@GetMapping("/chatRoom")
	public String chat() {
		return "page/chat/chat";
	}
	
    
	@Controller
	public class GreetingController {
	    @MessageMapping("/enter")
	    @SendTo("/topic/greetings")
	    public Greeting enter(HelloMessage message, StompHeaderAccessor session) throws Exception {
	        return new Greeting(HtmlUtils.htmlEscape(session.getSessionAttributes().get("name") + "님께서 입장하셨습니다!"));
	    }
	    @MessageMapping("/exit")
	    @SendTo("/topic/greetings")
	    public Greeting exit(HelloMessage message, StompHeaderAccessor session) throws Exception {
	        return new Greeting(HtmlUtils.htmlEscape(session.getSessionAttributes().get("name") + "님께서 퇴장하셨습니다!"));
	    }
	    @MessageMapping("/chat")
	    @SendTo("/topic/greetings")
	    public Greeting chat(HelloMessage message, StompHeaderAccessor session) throws Exception {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        Date now = new Date();

	        String currentTime = format.format(now);

	        System.out.println(currentTime);
	        return new Greeting(HtmlUtils.htmlEscape(session.getSessionAttributes().get("name") + " : "+message.getChat())+"["+currentTime+"]");
	    }


	}

	
	

}
