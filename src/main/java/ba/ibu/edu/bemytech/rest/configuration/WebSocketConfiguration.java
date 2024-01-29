package ba.ibu.edu.bemytech.rest.configuration;

import ba.ibu.edu.bemytech.rest.websockets.MainSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired
    MainSocketHandler mainSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("WebSocket handlers registered.");
        registry.addHandler(mainSocketHandler, "/websocket").setAllowedOrigins("*");
    }
}