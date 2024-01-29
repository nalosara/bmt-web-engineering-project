package ba.ibu.edu.bemytech.rest.websockets;

import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.service.JwtService;
import ba.ibu.edu.bemytech.core.service.UserService;
import ba.ibu.edu.bemytech.rest.dto.ProductRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class MainSocketHandler implements WebSocketHandler {

    private final JwtService jwtService;
    private final UserService userService;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public MainSocketHandler(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uri = session.getUri().toString();
        String token =  uri.substring(60);
        User user = getUser(token);
        if (user == null) {
            session.close();
            return;
        }

        sessions.put(user.getId(), session);
        System.out.println("Session created for the user " + user.getUsername() + " where the session id is " + session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Error happened " + session.getId() + " with reason ### " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        User disconnectedUser = findUserBySession(session);
        if (disconnectedUser != null) {
            sessions.remove(disconnectedUser.getId());
            System.out.println("Connection closed for user " + disconnectedUser.getId() + " with status ### " + closeStatus.getReason());
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageReceived = (String) message.getPayload();
    }

    public User getUser(String token) throws IOException {
        String userName = jwtService.extractUserName(token);

        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userName);
        if (userDetails instanceof User) {
            return (User) userDetails;
        } else {
            System.out.println("UserDetails is not an instance of User");
            return null;
        }
    }

    private User findUserBySession(WebSocketSession session) {
        return sessions.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(session))
                .map(entry -> {
                    try {
                        return userService.findById(entry.getKey());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(userDetails -> userDetails instanceof User)
                .map(userDetails -> (User) userDetails)
                .findFirst()
                .orElse(null);
    }

    public void broadcastNewProductAdded(ProductRequestDTO newProduct) {
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("msgType", "new product added");
            jsonMap.put("product", newProduct);
            String jsonString = new ObjectMapper().writeValueAsString(jsonMap);
            TextMessage message = new TextMessage(jsonString);

            sessions.forEach((sessionId, session) -> {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
