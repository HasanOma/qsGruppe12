package com.example.qsgruppe12.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Slf4j
@Component
public class WebSocketListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //WebSocket session created
        if (event instanceof SessionConnectedEvent){
            StompHeaderAccessor sha = StompHeaderAccessor.wrap(((SessionConnectedEvent) event).getMessage());
            log.info("SessionConnectedEvent: STOMP WebSocket session created for the user: {}", sha.getUser().getName());
        }

        //subscribed to websocketSession
        if (event instanceof SessionSubscribeEvent){
            StompHeaderAccessor sha = StompHeaderAccessor.wrap(((SessionSubscribeEvent) event).getMessage());
            log.info("SessionSubscribeEvent: User {} subscribed to WebSocket session, destination: {}", sha.getUser().getName(), sha.getDestination());
        }
    }
//
//            if (appEvent instanceof BrokerAvailabilityEvent){
//                logger.info("BrokerAvailabilityEvent: {}", appEvent.toString());
//            }
}
