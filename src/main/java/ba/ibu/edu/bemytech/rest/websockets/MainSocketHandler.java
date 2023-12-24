package ba.ibu.edu.bemytech.rest.websockets;

import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.service.JwtService;
import ba.ibu.edu.bemytech.core.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.messaging.simp.SimpMessageHeaderAccessor.getUser;

@Component
public class MainSocketHandler implements WebSocketHandler {
    private final JwtService jwtService;
    private final UserService userService;
    public Map<String, WebSocketSession> sessions = new HashMap<>();


    public MainSocketHandler(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        User user = getUser(session);
        if (user == null) {
            return;
        }
        sessions.put(user.getId(), session);
        System.out.println("Session created for the user " + user.getId() + " where the session id is " + session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Error happened " + session.getId() + " with reason ### " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Connection closed for session " + session.getId() + " with status ### " + closeStatus.getReason());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageReceived = (String) message.getPayload();
        System.out.println("Message received: " + messageReceived);
    }


}
