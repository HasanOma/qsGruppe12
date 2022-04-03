package com.example.qsgruppe12.config;

import lombok.Value;
import org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    long sessionTimeoutInSecs = 100000;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/queue");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/notify").addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        // in milliseconds
        container.setMaxSessionIdleTimeout(sessionTimeoutInSecs * 1000);
        return container;
    }

    /**
     * DefaultSimpUserRegistry is the replacement of MySessionRegistry ( Custom UserSessionRegistry ) after upgrade to Spring 5.
     * Below required with Spring 4.
     * import org.springframework.messaging.simp.user.UserSessionRegistry;
     @Repository
     public class MySessionRegistry implements UserSessionRegistry, ApplicationListener<AbstractSubProtocolEvent> {
     *
     */
    @Bean
    public DefaultSimpUserRegistry defaultSimpUserRegistry() {
        DefaultSimpUserRegistry userRegistry = new DefaultSimpUserRegistry();
        return userRegistry;
    }
}