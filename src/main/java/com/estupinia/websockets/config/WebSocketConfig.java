package com.estupinia.websockets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.estupinia.interceptors.AuthChannelInterceptorAdapter;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
    private AuthChannelInterceptorAdapter channelInterceptor;
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	config.enableSimpleBroker("/userresponse", "/queue" ,"/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
    
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        // Add our interceptor for authentication/authorization
        registration.interceptors(channelInterceptor);

    }
    
}
