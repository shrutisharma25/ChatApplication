package com.webscocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer{
	
	//server configure
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		// The connectivity for sending the message will the done form this "/server1" url
		// i.e. To connect with server
		registry.addEndpoint("/server1").withSockJS();
		//.setAllowedOrigins("*")
		
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		// broker will be enabled in this url
		// i.e to subscribe so that we can recieve messages
		// /topic/return-to
		registry.enableSimpleBroker("/topic");
	
		// for sending message the client need to first go for the "/app" url and then "/message"
		// i.e "/app/message"
		registry.setApplicationDestinationPrefixes("/app");
	}
	

}
