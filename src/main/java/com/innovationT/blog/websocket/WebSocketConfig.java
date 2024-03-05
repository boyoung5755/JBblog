package com.innovationT.blog.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.innovationT.blog.websocket.chat.ChannelInboundInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker   // 웹소켓 메시지를 다룰 수 있게 허용
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	private final ChannelInboundInterceptor channelInboundInterceptor;

	

	/*
	 * 웹소켓 통신을 하기 위해 엔드포인트를 설정한다.
		WebSocketHandler를 선언한다. 이 핸들러가 웹소켓 통신을 처리해준다.
		addHandler() 안에 첫번째 인자로 들어가는데, 여기에 new WebSocketChatHandler() 와 같이 
		사용자 정의 핸들러를 직접 넣어도 된다. 어차피 WebSocketHandler를 상속할 것이므로 상관없다.
		WebSocketHandlerRegistry에 웹소켓 엔드포인트를 /ws/chat로 설정.
		이제 ws://주소:포트/ws/chat로 요청이 들어오면 웹소켓 핸드쉐이킹을 한다.
		setAllowedOrigins("*")는 모든 cors 요청을 허용하는 것이다.
	 */
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); //발행자가 "/topic"의 경로로 메시지를 주면 구독자들에게 전달
        config.setApplicationDestinationPrefixes("/app"); // 발행자가 "/app"의 경로로 메시지를 주면 가공을 해서 구독자들에게 전달
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS(); // 커넥션을 맺는 경로 설정. 만약 WebSocket을 사용할 수 없는 브라우저라면 다른 방식을 사용하도록 설정
    }
    
    
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(channelInboundInterceptor);
    }
	

}
